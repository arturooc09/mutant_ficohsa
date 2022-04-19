package co.com.adn.domain.adnPerson.service;

import co.com.adn.adapter.error.ExceptionBuilder;
import co.com.adn.domain.adnPerson.repository.AdnBdRepository;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import co.com.adn.domain.adnPerson.service.model.Response.AdnStatic;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static co.com.adn.domain.adnPerson.GetMutantConts.*;
@Slf4j
@Service
@NoArgsConstructor
public class MutantGetService {

    @Autowired
    private AdnBdRepository adnBdRepository;

    private final StringBuffer dnaAux = new StringBuffer();
    private int contAdn;

    public MutantGetService(AdnBdRepository adnBdRepository) {

    }

    public Boolean isMutant(
            Map<String, String> headers,
            Adn adn,
            String remoteAddress) throws ExceptionBuilder {
        contAdn =0;
        final Adn dnaPerson = adn;
        String adnString = String.join(",", dnaPerson.getDna());

        for (String colum :adn.getDna()) {
            for (char letraAdn: DNA_VALID) {
                if(colum.toUpperCase(Locale.ROOT).contains(String.valueOf(letraAdn)))
                    throw new ExceptionBuilder(ADN_CODE_NOT_VALID,ADN_DESC_NOT_VALID);
            }
        }

        List<char[]> dnaMatrix = Arrays.stream(dnaPerson.getDna()).map(String::toCharArray).collect(Collectors.toList());
        if((dnaMatrix.size() != dnaMatrix.get(0).length) || adn.getDna().length < ADN_COUNT_MINIMUM)
            return Boolean.FALSE;
        isMutantValidate(adnString);
        if (isValidateAdnCount()) return Boolean.TRUE;
        isMutantValidate(transposeMatrix(dnaMatrix));
        try {
            adnBdRepository.RegisterAdn(adn, (contAdn >= 2) ? 1 : 0);
        }catch (SQLException | IOException | GeneralSecurityException | ClassNotFoundException e){
           throw new ExceptionBuilder("1-",e.getMessage());
        }
        return isValidateAdnCount();
    }

    public AdnStatic getStats(){
        AdnStatic adnStatic = new AdnStatic();
       List<Double> accountAdn =adnBdRepository.getAdn();
       if(accountAdn.size() > 0) {
           adnStatic.setCount_human_dna(accountAdn.get(0));
           adnStatic.setCount_mutant_dna(accountAdn.size() > 1 ? accountAdn.get(1) : 0);

       if(adnStatic.getCount_human_dna() > 0)
            adnStatic.setRatio(Double.valueOf(adnStatic.getCount_mutant_dna()/adnStatic.getCount_human_dna()));
       else
           adnStatic.setRatio(0.0);
       }
        return adnStatic;

    }

    public void isMutantValidate(String dnaPerson) {
        for (String adnMutan : DNA_MUTANT) {
            if (dnaPerson.contains(adnMutan)) {
                contAdn++;
            }
        }
    }

    public String transposeMatrix(List<char[]> dnaMatriz) throws ExceptionBuilder {
        StringBuilder adnTransponse = new StringBuilder();
        try {
            for (int i = 0; i < dnaMatriz.size(); i++) {
                for (int j = 0; j < dnaMatriz.size(); j++) {
                    adnTransponse.append(dnaMatriz.get(j)[i]);
                }
                adnTransponse.append(",");
            }
            log.info("TRANSPOSE " + adnTransponse);
        }catch (Exception e){
            throw new ExceptionBuilder(ADN_ERROR_RESULT,e.getMessage());
        }
        return adnTransponse.toString().concat(DiagonalMatrix(dnaMatriz));
    }

    public String DiagonalMatrix(List<char[]> dnaMatriz) throws ExceptionBuilder {
        int countMatriz = dnaMatriz.size();
        StringBuilder adnDiagonal = new StringBuilder();
        try {
            for (int diagonal = 4 - countMatriz; diagonal <= countMatriz - 1; diagonal += 1) {
                for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                     vertical < countMatriz && horizontal < countMatriz;
                     vertical += 1, horizontal += 1
                ) {
                    adnDiagonal.append(dnaMatriz.get(vertical)[horizontal]);
                    if (vertical == countMatriz - 4 && horizontal == 0)
                        diagonal = countMatriz;
                }
                adnDiagonal.append(",");
            }
        }catch (Exception e){
            throw new ExceptionBuilder(ADN_ERROR_RESULT2,e.getMessage());
        }
        log.info("DIAGONAL " + adnDiagonal);
        return adnDiagonal.toString();
    }

    public boolean isValidateAdnCount(){
        if (contAdn >= 2)
            return Boolean.TRUE;
        return  Boolean.FALSE;
    }

  }
