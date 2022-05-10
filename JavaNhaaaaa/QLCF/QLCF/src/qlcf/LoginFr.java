
package qlcf;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import Database.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginFr extends javax.swing.JFrame {
    
    private Connection conn=null;
    private ResultSet rs=null;
    
    private MyDatabase SQL;
    
    public LoginFr() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        
        SQL=new MyDatabase(new SQL());
        conn=SQL.connection("THANHTRUNG", 1433, "QuanCaPhe", "sa", "sa2016");
    }
    
    private boolean checkNull(){
        if(tfTaiKhoan.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập tài khoản đăng nhập!");
            return false;
        }
        else
        if(pass.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mật khẩu!");
            return false;
        }
        return true;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Photos/coffee.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        lbUser = new javax.swing.JLabel();
        lbPass = new javax.swing.JLabel();
        tfTaiKhoan = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });

        lbUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbUser.setText("Tài khoản:");

        lbPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPass.setText("Mật khẩu:");

        tfTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        pass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/login.png"))); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/x-button.png"))); // NOI18N
        btnCancel.setText("Thoát");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lbTrangthai.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lbTrangthai.setForeground(new java.awt.Color(255, 255, 0));
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbUser)
                            .addComponent(lbPass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(pass))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addGap(43, 43, 43))))
            .addComponent(lbTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(tfTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPass)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorMoved
      
    }//GEN-LAST:event_formAncestorMoved

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(checkNull())
        try {
            String sql="SELECT * FROM QLNV";
            
            rs=SQL.excuteQuery( conn, sql);
            
            while(rs.next()){
                if(rs.getString("taiKhoan").toString().trim().equals(tfTaiKhoan.getText())&& rs.getString("matKhau").toString().trim().equals(pass.getText())){
                    
                    Detail detail=new Detail(rs.getString("taiKhoan").trim(),rs.getString("tenNV").trim());
                    
                    MainFr home = new MainFr(detail);
                    this.setVisible(false);
                    home.setVisible(true);
                }
                else lbTrangthai.setText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField tfTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
