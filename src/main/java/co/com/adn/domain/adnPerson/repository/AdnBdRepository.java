package co.com.adn.domain.adnPerson.repository;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;

public interface AdnBdRepository {

    void RegisterAdn(Adn adn, int isMutant ) throws IOException, SQLException, GeneralSecurityException, ClassNotFoundException;
    List<Double> getAdn();

}
