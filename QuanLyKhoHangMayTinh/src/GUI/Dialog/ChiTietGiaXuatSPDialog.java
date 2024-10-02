package GUI.Dialog;

import BUS.ThongKeBUS;
import DTO.DateRangeDTO;
import DTO.ThongKe.ChiTietGiaXuatSPDTO;
import DTO.ThongKe.ChiTietLoaiSanPhamDTO;
import GUI.ThongKeGUI;
import helper.CustomTableCellRenderer;
import helper.DateHelper;
import helper.NumberHelper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class ChiTietGiaXuatSPDialog extends PriceDetailDialog {
    private final ThongKeBUS tkBUS = new ThongKeBUS();
    private ArrayList<ChiTietGiaXuatSPDTO> arr;
    
    public ChiTietGiaXuatSPDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    public ChiTietGiaXuatSPDialog(JDialog parent, boolean modal, ChiTietLoaiSanPhamDTO product, DateRangeDTO dateRange) {
        super(getOwnerFrame(parent), modal);
        initTable();
        displayInfo(product, dateRange);
        getChiTietGiaXuatSanPham(product.getMaSanPham(), dateRange);
    }

    public ArrayList<ChiTietGiaXuatSPDTO> getArr() {
        return arr;
    }

    public void setArr(ArrayList<ChiTietGiaXuatSPDTO> arr) {
        this.arr = arr;
    }
    
    private void displayInfo(ChiTietLoaiSanPhamDTO product, DateRangeDTO dateRange) {
        super.setTitle("Chi tiết giá xuất sản phẩm");
        getLblPrimary().setText("Tên sản phẩm: " + product.getTenSanPham());
        getLblSecondary().setVisible(false);
        if (dateRange.getFromDate() == null && dateRange.getToDate() == null) {
            getLblTime().setText(ThongKeGUI.CB_VALUE_LIFETIME);
        } else {
            getLblTime().setText("Thời gian: " + dateRange.getFromDate().format(DateHelper.DATE_FORMATTER) + " - " + dateRange.getToDate().format(DateHelper.DATE_FORMATTER));         
        }
    }
    
    private void getChiTietGiaXuatSanPham(int productId, DateRangeDTO dateRange) {
        ArrayList<ChiTietGiaXuatSPDTO> arr = new ArrayList<>();
        try {
            arr = tkBUS.chiTietGiaXuatSanPham(productId, dateRange);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ChiTietGiaXuatSPDialog.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ChiTietGiaXuatSPDialog.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
        model.setRowCount(0);
        if (arr.isEmpty()) return;
        setArr(arr);
        for (int i = 0; i < arr.size(); ++i) {
            ChiTietGiaXuatSPDTO ctgx = arr.get(i);
            int maPhieuXuat = ctgx.getMaPhieuXuat();
            LocalDateTime thoiGianTao = ctgx.getThoiGianTao();
            String strThoiGianTao = thoiGianTao.format(DateHelper.DATE_TIME_FORMATTER);
            int soLuongXuat = ctgx.getSoLuongXuat();
            Long donGiaXuat = ctgx.getDonGiaXuat();
            String strDonGiaXuat = NumberHelper.appendVND(NumberHelper.commafy(donGiaXuat));
            Object [] row = {maPhieuXuat, strThoiGianTao, soLuongXuat, strDonGiaXuat};
            model.addRow(row);
        }
        for (int i = 0; i < getTable().getColumnCount(); ++i) {
            switch (i) {
                case 0:
                case 1:
                    getTable().getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.CENTER);
                    break;
                case 2:
                case 3:
                    getTable().getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
                    break;
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ChiTietGiaXuatSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietGiaXuatSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietGiaXuatSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietGiaXuatSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietGiaXuatSPDialog dialog = new ChiTietGiaXuatSPDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Override
    public void initTable() {
        getTable().setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu xuất", "Thời gian tạo", "Số lượng xuất", "Đơn giá xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
