package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    // quantity of coffee
    int quantity = 0;
    // whipped cream checkbox variable
    boolean hasWhippedCream = false;
    // chocolate checkbox variable
    boolean hasChocolate = false;
    // user name variable
    String userName = "anonymous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();

        EditText editText = (EditText) findViewById(R.id.user_name);
        userName = editText.getText().toString();

        String orderSummaryBook = createOrderSummary(price);
        displayMessage(orderSummaryBook);
    }

    /**
     * The method check on the whipped cream checkbox state
     * this method is not wrong but different than what Katherine
     * use in the tutorial
     * Katherine use casting on the CheckBox subclass
     */
    public void onCheckBoxClicked(View view){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();

        // Is the view now checked?
    //    boolean checked = ((CheckBox) view).isChecked();

        // check the hasWhippedCream state
    //    if (checked)
    //        hasWhippedCream = true;
    //    else
    //        hasWhippedCream = false;

    }

    /**
     * This method receive name input
     */
    //public void nameInput(View view){

    //}

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(){
        return quantity * 5;
    }

    /**
     * This method create summary of the order that contain name, quantity and price
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price){
        String orderSummary = "Name: " + userName;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal : $" + price;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numA) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numA);
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view){
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}