package co.com.adn.adapter.bd;

import co.com.adn.domain.adnPerson.repository.AdnBdRepository;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static co.com.adn.adapter.bd.BdConsts.*;

@Slf4j
@Repository
public class SqlRepository implements AdnBdRepository {

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${urldb}")
    private String urldb;

    @Value("${insert}")
    private String insert;

    @Value("${consult}")
    private String consult;

     public Connection createSqlService(){

         Connection con = null;
         try{
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(urldb,user,password);
         }catch (SQLException sqle){
             log.info("Error Connection SQL "+sqle.getMessage());
         }catch (ClassNotFoundException sqle){
             log.info("Error Connection SQL "+sqle.getMessage());
         }
         return con;
    }

    public void closeSqlService(Connection connection)  {
         try {
             connection.close();
         }catch (SQLException sqle){
             log.info("Error cerrando Connection SQL "+sqle.getSQLState()+ " MESSAGE "+sqle.getMessage());
         }
    }

    @Override
    public void RegisterAdn(Adn adn, int isMutant) {
        Connection con = createSqlService();
        try(PreparedStatement smt = con.prepareStatement(insert.concat(String.join(",", adn.getDna())+"','"+isMutant+"')"))) {
            Boolean rs = smt.execute();
            log.info("CREATE " + rs);
        }catch (SQLException sqle){
            log.info("Error insert SQL "+sqle.getSQLState()+ " MESSAGE "+sqle.getMessage());
        }finally {
            closeSqlService(con);
        }
    }

    @Override
    public List<Double> getAdn() {
        List<Double> countADN = new ArrayList<>();
        Connection con = createSqlService();
        try(PreparedStatement smt = con.prepareStatement(consult)) {
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                countADN.add(Double.valueOf(rs.getString(POSITION_ONE)));
            countADN.add(Double.valueOf(rs.getString(POSITION_TWO)));
            }
            log.info("Consulta " + rs);
        }catch (SQLException sqle){
            log.info("Error consult SQL "+sqle.getSQLState()+ " MESSAGE "+sqle.getMessage());
        }finally {
            closeSqlService(con);
        }
        return countADN;
    }
}
