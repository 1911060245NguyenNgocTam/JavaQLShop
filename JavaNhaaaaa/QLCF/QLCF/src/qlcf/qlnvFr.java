
package qlcf;

import Database.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class qlnvFr extends javax.swing.JFrame {

    private Connection conn=null;
    private ResultSet rs=null;
    
    private String sql="SELECT * FROM QLNV ORDER BY maNV";
    
    private boolean add=false, change=false;
    
    private Detail detail;
    
    private MyDatabase SQL;
    
    public qlnvFr(Detail d) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        
        SQL=new MyDatabase(new SQL());
        
        conn=SQL.connection("THANHTRUNG", 1433, "QuanCaPhe", "sa", "sa2016");
        
        Disabled();
        loadData(sql);
        detail=new Detail(d);
        checkDecentralization();
    }
    
    private void loadData(String sql){
        try{
            String[] arry={"Mã Nhân Viên","Họ Tên","Giới Tính","Ngày Sinh","SĐT","Địa Chỉ"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            
            rs=SQL.excuteQuery(conn, sql);
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("maNV").trim());
                vector.add(rs.getString("tenNV").trim());
                vector.add(rs.getString("gioiTinh").trim());
                vector.add(rs.getString("ngaySinh").trim());
                vector.add(rs.getString("sdt").trim());
                vector.add(rs.getString("diaChi").trim());
                model.addRow(vector);
            }
            tableNV.setModel(model);
            SQL.closeResultSet( rs);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
    private void Enabled(){
        tfMaNV.setEnabled(true);
        tfHoten.setEnabled(true);
        tfNgaysinh.setEnabled(true);
        tfSDT.setEnabled(true);
        tfTaikhoan.setEnabled(true);
        pass.setEnabled(true);
        passConfirm.setEnabled(true);
        tfDiachi.setEnabled(true);
        rbNu.setEnabled(true);
        rbNam.setEnabled(true);
    }
    
    private void Disabled(){
        tfMaNV.setEnabled(false);
        tfHoten.setEnabled(false);
        tfNgaysinh.setEnabled(false);
        tfSDT.setEnabled(false);
        tfTaikhoan.setEnabled(false);
        pass.setEnabled(false);
        passConfirm.setEnabled(false);
        tfDiachi.setEnabled(false);
        rbNu.setEnabled(false);
        rbNam.setEnabled(false);
    }
    
    private void reset(){
        add=false;
        change=false;
        tfMaNV.setText("");
        tfHoten.setText("");
        ((JTextField)tfNgaysinh.getDateEditor().getUiComponent()).setText("");
        tfSDT.setText("");
        tfTaikhoan.setText("");
        pass.setText("");
        passConfirm.setText("");
        tfDiachi.setText("");
        lbTrangthai.setText("Trạng Thái");
        rbNam.setSelected(false);
        btnEdit.setEnabled(false);
        btnDel.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnAdd.setEnabled(true);
    }

    private void checkGT(String GT){
        if(GT.equals("Nam"))
            rbNam.setSelected(true);
        else
            rbNu.setSelected(true);
    }
    
    private boolean checkNull(){
        if(tfMaNV.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mã nhân viên!");
            return false;
        }
        else
        if(tfHoten.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập họ tên nhân viên!");
            return false;
        }
        else
        if(rbNam.isSelected()==false && rbNu.isSelected()==false){
            lbTrangthai.setText("Bạn chưa chọn giới tính!");
            return false;
        }
        else
        if(((JTextField)tfNgaysinh.getDateEditor().getUiComponent()).getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập ngày sinh!");
            return false;
        }
        else   
        if(tfSDT.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập số điện thoại!");
            return false;
        }
        else   
        if(tfDiachi.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập địa chỉ!");
            return false;
        }
        else
        if(tfTaikhoan.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập tài khoản!");
            return false;
        }
        else
        if(pass.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mật khẩu!");
            return false;
        }
        else
        if(passConfirm.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập lại mật khẩu!");
            return false;
        }
        else
        if(String.valueOf(pass.getPassword()).equals(String.valueOf(passConfirm.getPassword()))){
            return true;
        }
        else {
            lbTrangthai.setText("Nhập lại mật khẩu không đúng!");
            return false;
        }
    }
    
    private String gioiTinh(){
        if(rbNam.isSelected())
            return rbNam.getText();
        else
            return rbNu.getText();
    }
    
    private void addNV(){
        if(checkNull()){
            String sqlAdd="INSERT INTO QLNV (maNV,tenNV,gioiTinh,ngaySinh,sdt,diaChi,taiKhoan,matKhau) VALUES ('"+tfMaNV.getText()+"',N'"+tfHoten.getText()+"',N'"+gioiTinh()+"','"+((JTextField)tfNgaysinh.getDateEditor().getUiComponent()).getText()+"','"+tfSDT.getText()+"',N'"+tfDiachi.getText()+"','"+tfTaikhoan.getText()+"','"+pass.getText()+"')";
            
            SQL.excuteUpdata(conn, sqlAdd);
            
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Thêm nhân viên thành công!");
        }
    }
    
    private void changeNV(){
        if(checkNull()){
            int click=tableNV.getSelectedRow();
            TableModel model=tableNV.getModel();
        
            String sqlChange="UPDATE QLNV SET maNV='"+tfMaNV.getText()+"', tenNV=N'"+tfHoten.getText()+"', gioiTinh=N'"+gioiTinh()+"', ngaySinh='"+((JTextField)tfNgaysinh.getDateEditor().getUiComponent()).getText()+"', sdt='"+tfSDT.getText()+"',diaChi=N'"+tfDiachi.getText()+"',taiKhoan='"+tfTaikhoan.getText()+"',matKhau='"+pass.getText()+"' WHERE maNV='"+model.getValueAt(click, 0)+"'";
            
            SQL.excuteUpdata(conn, sqlChange);
            
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Sửa thông tin nhân viên thành công!");
        }
    }
    
    private boolean check(){
        try {
            
            rs=SQL.excuteQuery( conn, sql);
            
            while(rs.next()){
                if(rs.getString("maNV").toString().trim().equals(tfMaNV.getText())){
                    lbTrangthai.setText("Mã nhân viên bạn nhập đã tồn tại!");
                    return false;
                }
                else
                if(rs.getString("taiKhoan").toString().trim().equals(tfTaikhoan.getText())){
                    lbTrangthai.setText("Tài khoản bạn nhập đã tồn tại!");
                    return false;
                }
            }
            SQL.closeResultSet( rs);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    private void loadAccount(String s){
        try{
            String sql="SELECT * FROM QLNV WHERE maNV='"+s+"'";
            
            rs=SQL.excuteQuery( conn, sql);
            while(rs.next()){
                tfTaikhoan.setText(rs.getString("taiKhoan").trim());
                pass.setText(rs.getString("matKhau").trim());
                passConfirm.setText(rs.getString("matKhau").trim());
            }
            SQL.closeResultSet(rs);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void checkDecentralization(){
        if(String.valueOf(detail.getUser().substring(0, 4)).equals("User")){
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
            btnDel.setEnabled(false);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
        }
    }
    
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbQLNV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbMaNV = new javax.swing.JLabel();
        lbHoten = new javax.swing.JLabel();
        lbGioitinh = new javax.swing.JLabel();
        lbNgaysinh = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbDiachi = new javax.swing.JLabel();
        tfMaNV = new javax.swing.JTextField();
        tfHoten = new javax.swing.JTextField();
        tfSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTaikhoan = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        tfDiachi = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        passConfirm = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        tfNgaysinh = new com.toedter.calendar.JDateChooser();
        btnFind = new javax.swing.JButton();
        tfFind = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý nhân viên");

        lbQLNV.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lbQLNV.setText("Quản lý nhân viên");

        tableNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        lbMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV.setText("Mã Nhân viên:");

        lbHoten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHoten.setText("Họ và Tên:");

        lbGioitinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGioitinh.setText("Giới tính:");

        lbNgaysinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNgaysinh.setText("Ngày sinh:");

        lbSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT.setText("Số điện thoại:");

        lbDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi.setText("Địa chỉ:");

        tfMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfHoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tfSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSDTKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tài khoản:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu:");

        tfTaikhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        pass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbNu.setText("Nữ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Thông tin");

        passConfirm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Xác nhận lại mật khẩu:");

        tfNgaysinh.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbSDT)
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHoten, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNgaysinh, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbDiachi, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfSDT)
                            .addComponent(tfHoten)
                            .addComponent(tfDiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(tfNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(lbGioitinh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(rbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(211, 211, 211))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMaNV)
                            .addComponent(tfMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHoten)
                            .addComponent(tfHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGioitinh)
                            .addComponent(rbNu)
                            .addComponent(rbNam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNgaysinh)
                            .addComponent(jLabel1)
                            .addComponent(tfTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tfNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiachi)
                    .addComponent(jLabel4)
                    .addComponent(passConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/search (1).png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        tfFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/user (1).png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/user(2).png"))); // NOI18N
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/user.png"))); // NOI18N
        btnDel.setEnabled(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/floppy-disk (1).png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/x-button.png"))); // NOI18N
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnSave)
                    .addComponent(btnDel)))
        );

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/smart-home.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lbTrangthai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng Thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(259, 259, 259)
                        .addComponent(lbQLNV)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfFind)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbQLNV))
                    .addComponent(btnBack))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        reset();
        add=true;
        Enabled();
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        add=false;
        change=true;
        Enabled();
        btnAdd.setEnabled(false);
        btnDel.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            
            String sqlDelete="DELETE FROM QLNV WHERE maNV='"+tfMaNV.getText()+"'";
            
            SQL.excuteUpdata( conn, sqlDelete);
            
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Xóa nhân viên thành công!");
        }
        else reset();
    }//GEN-LAST:event_btnDelActionPerformed
    
    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        int click=tableNV.getSelectedRow();
        TableModel model=tableNV.getModel();
        
        tfMaNV.setText(model.getValueAt(click, 0).toString());
        tfHoten.setText(model.getValueAt(click, 1).toString());
        ((JTextField)tfNgaysinh.getDateEditor().getUiComponent()).setText(model.getValueAt(click, 3).toString());
        tfSDT.setText(model.getValueAt(click, 4).toString());
        tfDiachi.setText(model.getValueAt(click, 5).toString());
        
        checkGT(model.getValueAt(click, 2).toString());
        loadAccount(model.getValueAt(click, 0).toString());
        
        btnEdit.setEnabled(true);
        btnDel.setEnabled(true);
        checkDecentralization();
    }//GEN-LAST:event_tableNVMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SQL.closeConnection(conn);
        MainFr home = new MainFr(detail);
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(add==true){
            if(check()){
                addNV();
            }
        }
        else{
            if(change==true)
                changeNV();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        reset();
        Disabled();
        loadData(sql);
        checkDecentralization();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String sql = "SELECT * FROM QLNV where maNV like N'%"+tfFind.getText()+"%' or tenNV like N'%"+tfFind.getText()+"%' or gioiTinh like N'%"+tfFind.getText()+"%' or ngaySinh like N'%"+tfFind.getText()+"%' or sdt like N'%"+tfFind.getText()+"%' or diaChi like N'%"+tfFind.getText()+"%' or taiKhoan like N'%"+tfFind.getText()+"%' or matKhau like N'%"+tfFind.getText()+"'";
        Disabled();
        loadData(sql);
        tfFind.setText("");
        reset();
        checkDecentralization();
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnFindActionPerformed

    private void tfSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSDTKeyReleased
        tfSDT.setText(cutChar(tfSDT.getText()));
        
        if(tfSDT.getText().length()==11 || tfSDT.getText().length()==10 ){
            
            btnSave.setEnabled(true);
            lbTrangthai.setText("Số điện thoại đã hợp lệ!!");
        }
        else
        if(tfSDT.getText().length()>11 || tfSDT.getText().length()<10){
            btnSave.setEnabled(false);
            lbTrangthai.setText("Số điện thoại không được nhỏ hơn 10 số hoặc vượt quá 11 số!!");
        }
    }//GEN-LAST:event_tfSDTKeyReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(qlnvFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(qlnvFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(qlnvFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(qlnvFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail= new Detail();
                new qlnvFr(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiachi;
    private javax.swing.JLabel lbGioitinh;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbNgaysinh;
    private javax.swing.JLabel lbQLNV;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPasswordField passConfirm;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField tfDiachi;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfHoten;
    private javax.swing.JTextField tfMaNV;
    private com.toedter.calendar.JDateChooser tfNgaysinh;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTaikhoan;
    // End of variables declaration//GEN-END:variables
}
