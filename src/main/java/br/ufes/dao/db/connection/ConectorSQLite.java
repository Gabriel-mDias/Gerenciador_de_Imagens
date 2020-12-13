package br.ufes.dao.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorSQLite {
    
    private Connection conector = null;
    
    public Connection conectar() {
        try {
            if (conector == null || conector.isClosed()) {
                String url = "jdbc:sqlite:src/main/java/br/ufes/dao/db/gerenciadorImagens.db";
                conector = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conector;
    }
    
    public void close() {
        close(this.conector);
    }
    
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abreTransacao() throws SQLException {
        if (this.conector == null) {
            throw new RuntimeException("Conexão não ativa");
        }
        
        PreparedStatement ps = conector.prepareStatement("BEGIN TRANSACTION;");
        ps.execute();
    }
    
    public void fechaTransacao() throws SQLException {
        if (this.conector == null) {
            throw new RuntimeException("Conexão não ativa");
        }
        
        PreparedStatement ps = conector.prepareStatement("COMMIT;");
        ps.execute();
    }
    
    public void desfazTransacao() throws SQLException {
        if (this.conector == null) {
            throw new RuntimeException("Conexão não ativa");
        }
        
        PreparedStatement ps = conector.prepareStatement("ROLLBACK;");
        ps.execute();
    }
}
