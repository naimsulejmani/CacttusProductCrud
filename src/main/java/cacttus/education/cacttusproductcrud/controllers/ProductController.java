package cacttus.education.cacttusproductcrud.controllers;

import cacttus.education.cacttusproductcrud.exceptions.ProductNotFoundException;
import cacttus.education.cacttusproductcrud.infrastructure.AlertDialogHelper;
import cacttus.education.cacttusproductcrud.models.Product;
import cacttus.education.cacttusproductcrud.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    private ProductRepository productRepository;

    @FXML
    private TextField txtProductId;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtSupplierId;
    @FXML
    private TextField txtCategoryId;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private CheckBox chbDiscontinued;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.productRepository = new ProductRepository();
    }

    public void resetForm() {
        txtProductId.setText("");
        txtCategoryId.setText("");
        txtSupplierId.setText("");
        txtProductName.clear();
        txtUnitPrice.clear();
        chbDiscontinued.setSelected(false);
    }

    private Product getProduct() {
        Product product = new Product();
        if (txtProductName.getText().isBlank())
            throw new RuntimeException("Product name is missing!");
        product.setProductName(txtProductName.getText());
        if (txtUnitPrice.getText().isBlank())
            throw new RuntimeException("Unit price is missing!");
        product.setUnitPrice(Float.parseFloat(txtUnitPrice.getText()));
        product.setCategoryId(Integer.parseInt(txtCategoryId.getText()));
        product.setSupplierId(Integer.parseInt(txtSupplierId.getText()));
        product.setDiscontinued(chbDiscontinued.isSelected());

        if (!txtProductId.getText().isBlank())
            product.setProductId(Integer.parseInt(txtProductId.getText()));

        return product;
    }

    public void save() {
        try {
            if (!txtProductId.getText().isBlank()) {
                update();
                return;
            }
            Product product = getProduct();
            this.productRepository.add(product);
            AlertDialogHelper.showAlert("SUCCESS", "DB-PALIDHJE",
                    "Product is saved successfully on database!",
                    Alert.AlertType.INFORMATION);
            resetForm();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("SUCCESS");
//            alert.setHeaderText("DB PALIDHJE");
//            alert.setContentText("Product with name " + product.getProductName()
//                    + " is saved successfully on database!");
//            alert.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            AlertDialogHelper.showAlert("GABIM!!", "GABIM SHUME KATASTROFIKE",
                    ex.getMessage(), Alert.AlertType.ERROR);
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Gabim!");
//            alert.setContentText(ex.getMessage());
//            alert.show();
        }
    }

    public void search() {
        try {
            int productId = Integer.parseInt(txtProductId.getText());
            Product product = this.productRepository.getById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product with id = " + productId + " not found!");
            }
            fillForm(product);
        } catch (ProductNotFoundException ex) {
            AlertDialogHelper.showAlert("Not found", "Missing", ex.getMessage(),
                    Alert.AlertType.WARNING);
        } catch (Exception ex) {
            AlertDialogHelper.showAlert("GABIM!", "GABIM SHUME PALIDHJE!",
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void fillForm(Product product) {
        txtProductName.setText(product.getProductName());
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        txtCategoryId.setText("" + product.getCategoryId());
        txtSupplierId.setText("" + product.getSupplierId());
        chbDiscontinued.setSelected(product.isDiscontinued());
    }

    public void update() {
        try {
            Product updatedProduct = getProduct();
            Product productDatabase = productRepository.getById(updatedProduct.getProductId());
            if (productDatabase != null) {
                productRepository.modify(updatedProduct);
                AlertDialogHelper.showAlert("SUCCESS", "MODIFY",
                        "U azhurnua me sukses", Alert.AlertType.INFORMATION);
            } else {
                throw new ProductNotFoundException("Producti nuk gjendet ne db per azhurnim!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}











