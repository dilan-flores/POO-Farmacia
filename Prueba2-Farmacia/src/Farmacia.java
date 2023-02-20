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
        JFormattedTextField cantidad = new JFormattedTextField();
        JFormattedTextField vencido = new JFormattedTextField();
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    Statement s = conexion.createStatement();

                    ResultSet rs = s.executeQuery("SELECT * FROM productos ");

                    cbCANTIDAD.removeAllItems();
                    cbVENCIDA.removeAllItems();
                    cbCANTIDAD.addItem(" ");
                    cbVENCIDA.addItem(" ");
                    while (rs.next()) {
                            cbCANTIDAD.addItem(rs.getString(4));
                            cbVENCIDA.addItem(rs.getString(5));
                    }

                    String id = textID.getText();
                    ResultSet r = s.executeQuery("SELECT * FROM productos WHERE id =" + id);



                    while (r.next()) {
                        if (textID.getText().equals(r.getString(1))) {
                            textNOMBRE.setText(r.getString(2));
                            textDESCRIPCION.setText(r.getString(3));
                            cantidad.setText(r.getString(4));
                            vencido.setText(r.getString(5));
                            cbCANTIDAD.setSelectedItem(cantidad.getText());
                            cbVENCIDA.setSelectedItem(vencido.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "DATOS NO ENCONTRADOS");
                        }
                    }
                    conexion.close();
                    rs.close();
                    r.close();
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
                    ps = conexion.prepareStatement("Insert into productos values (?,?,?,?,?)");
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());
                    cantidad.setText((String)cbCANTIDAD.getSelectedItem());
                    vencido.setText((String)cbVENCIDA.getSelectedItem());
                    ps.setString(4, cantidad.getText());
                    ps.setString(5, vencido.getText());
                    cbCANTIDAD.setSelectedItem(cantidad.getText());
                    cbVENCIDA.setSelectedItem(vencido.getText());

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
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    ps = conexion.prepareStatement("UPDATE productos SET id = ?,nombre = ?, descripcion= ?, cantidad =?, cantidad_vencidad =?  WHERE id = " + textID.getText());
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());

                    /*cbCANTIDAD.removeItem(cantidad.getText());
                    cbVENCIDA.removeItem(vencido.getText());*/
                    cantidad.setText((String)cbCANTIDAD.getSelectedItem());
                    vencido.setText((String)cbVENCIDA.getSelectedItem());
                    ps.setString(4, cantidad.getText());
                    ps.setString(5, vencido.getText());

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
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    ps = conexion.prepareStatement("DELETE from productos WHERE id = " + textID.getText() );

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CIUDADANO " + textID.getText() + " ELIMINADO");
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

