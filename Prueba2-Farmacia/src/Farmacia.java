import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Farmacia {
    PreparedStatement ps;
    public JPanel Panel;
    private JButton actualizar;
    private JButton crear;
    private JButton buscar;
    private JButton borrar;
    private JFormattedTextField textID;
    private JFormattedTextField textNOMBRE;
    private JFormattedTextField textDESCRIPCION;
    private JComboBox cbCANTIDAD;
    private JComboBox cbVENCIDA;

    public Farmacia() {
        /*
        textNOMBRE.setEnabled(false);
        textCELULAR.setEnabled(false);
        textEMAIL.setEnabled(false);
        actualizar.setEnabled(false);
         */

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    Statement s = conexion.createStatement();
                    String id = textID.getText();
                    ResultSet rs = s.executeQuery("SELECT * FROM productos WHERE id =" + id);

                    while (rs.next()) {
                        if (textID.getText().equals(rs.getString(1))) {
                            textNOMBRE.setText(rs.getString(2));
                            textDESCRIPCION.setText(rs.getString(3));
                            cbCANTIDAD.addItem(rs.getString(4));
                            cbVENCIDA.addItem(rs.getString(5));
                        } else {
                            JOptionPane.showMessageDialog(null, "DATOS NO ENCONTRADOS");
                        }
                    }
                    conexion.close();
                    rs.close();
                    s.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                /*
                textNOMBRE.setEnabled(true);
                textCELULAR.setEnabled(true);
                textEMAIL.setEnabled(true);
                actualizar.setEnabled(true);
                textID.setEnabled(false);
                buscar.setEnabled(false);
                 */
            }
        });
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    ps = conexion.prepareStatement("Insert into productos values = (?,?,?,?,?)");
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());
                    ps.setString(4, cbCANTIDAD.getName());
                    ps.setString(5, cbVENCIDA.getName());

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CREACIÓN CON ÉXITO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }

                    conexion.close();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                /*
                textNOMBRE.setEnabled(true);
                textCELULAR.setEnabled(true);
                textEMAIL.setEnabled(true);
                actualizar.setEnabled(true);
                textID.setEnabled(false);
                buscar.setEnabled(false);
                 */
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/Agencia", "root", "3001a"
                    );
                    ps = conexion.prepareStatement("UPDATE Ciudadanos SET id = ?,nombre = ?, celular= ?, emagil =? WHERE id = " + textID.getText());
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());
                    ps.setString(4, cbCANTIDAD.getName());
                    ps.setString(5, cbVENCIDA.getName());

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CIUDADANO " + textID.getText() + " ACTUALIZADO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }
                    //limpiartxt();
                    //textID.setText("");
                    //textNOMBRE.setText("");
                    //textCELULAR.setText("");
                    //textCORREO.setText("");
                    conexion.close();//importante!!!!
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/Agencia", "root", "3001a"
                    );
                    ps = conexion.prepareStatement("DELETE from productos WHERE id = " + textID.getText());
                    /*
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());
                    ps.setString(4, cbCANTIDAD.getSelectedItem())
                    ps.setString(4, cbVENCIDA.getSelectedItem())
                    */

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CIUDADANO " + textID.getText() + " ACTUALIZADO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }
                    //limpiartxt();
                    //textID.setText("");
                    //textNOMBRE.setText("");
                    //textCELULAR.setText("");
                    //textCORREO.setText("");
                    conexion.close();//importante!!!!
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

