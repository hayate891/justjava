package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    // quantity of coffee
    int quantity = 1;
    // whipped cream checkbox variable
    boolean hasWhippedCream = false;
    // chocolate checkbox variable
    boolean hasChocolate = false;
    // user name variable
    String userName = "anonymous";
    // raining state variable
    boolean isRaining = false;
    // whipped cream price is $1
    // chocolate price is $2
    // base coffee price is $5
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

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

        // Find the user's name
        EditText editTextUserName = (EditText) findViewById(R.id.user_name);
        userName = editTextUserName.getText().toString();

        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     * The method check on the whipped cream checkbox state
     * this method is not wrong but different than what Katherine
     * use in the tutorial
     * Katherine use casting on the CheckBox subclass
     */
    public void onCheckBoxClicked(View view) {
        // figure out if user want whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        // figure out if user want chocolate
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
    private int calculatePrice() {
        int price = 5;
        // check if both hasWhippedCream and hasChocolate is true first
        if (hasWhippedCream && hasChocolate) {
            return quantity * (5 + whippedCreamPrice + chocolatePrice);
        }
        // next check if hasChocolate is true
        else if (hasChocolate) {
            return quantity * (5 + chocolatePrice);
        }
        // and then check if hasWhippedCream is true
        else if (hasWhippedCream) {
            return quantity * (5 + whippedCreamPrice);
        }
        // if non of the checkbox is true then execute this statement
        else {
            return quantity * 5;
        }
    }

    /**
     * This method create summary of the order that contain name, quantity and price
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price) {
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
     * This method is called when the plus button is clicked.
     * This method increment the quantity by 1
     * This method prevent user ordering more than 100 by checking if quantity == 100
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        // increment quantity by 1 for each press
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     * This method decrement the quantity by 1
     * This method prevent user ordering less than 1 by using conditional operator ?:
     * disabling conditional operator for Katherine's if statement
     * So now this method prevent user ordering less than 1 by checking if quantity == 1
     */
    public void decrement(View view) {
        if(quantity == 1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            // Exit this method ear;y because there's nothing left to do
            return;
        }
        // decrement quantity by 1 for each press
        quantity = quantity - 1;
        //quantity = quantity > 0 ? quantity : 1;    // conditional operator ?:
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