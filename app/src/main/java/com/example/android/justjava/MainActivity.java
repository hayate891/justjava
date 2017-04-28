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

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    // quantity of coffee
    int quantity = 2;
    // user name variable
    String name = "anonymous";
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
     * Intent is use to send the order summary to email by using email intent
     */
    public void submitOrder(View view) {


        // Find the user's name
        EditText editTextUserName = (EditText) findViewById(R.id.user_name);
        name = editTextUserName.getText().toString();

        // figure out if user want whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        // figure out if user want chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(addWhippedCream, addChocolate);

        String priceMessage = createOrderSummary(name, addWhippedCream, addChocolate, price);
        displayMessage(priceMessage);

//        // Disabling this intent because it can't be use in the emulator
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + userName);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        //int price = 5;
        // check if both hasWhippedCream and hasChocolate is true first
        if (addWhippedCream && addChocolate) {
            return quantity * (5 + whippedCreamPrice + chocolatePrice);
        }
        // next check if hasChocolate is true
        else if (addChocolate) {
            return quantity * (5 + chocolatePrice);
        }
        // and then check if hasWhippedCream is true
        else if (addWhippedCream) {
            return quantity * (5 + whippedCreamPrice);
        }
        // if non of the checkbox is true then execute this statement
        else {
            return quantity * 5;
        }
    }

//    // this method failed. so im disabling it for now
//    /**
//     * This method will change boolean to yes or no
//     */
//    public String boolToString(boolean addWhippedCream){
//        return bool ? getString(R.string.yes) : getString(R.string.no);
//    }

    /**
     * THis method will change boolean addWhippedCream or addChocolate to yes or no
     * @param bool bool will accept any boolean input
     * @return returning string yes or no
     */
    public String boolToString(boolean bool){
        if (bool)
            return getString(R.string.yes);
        else
            return getString(R.string.no);
    }

    /**
     * This method create summary of the order that contain name, quantity and price
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate, int price) {
        String orderSummary = getString(R.string.order_summary_name, name);
        orderSummary += "\n" + getString(R.string.order_summary_whipped_cream, boolToString(addWhippedCream));
        orderSummary += "\n" + getString(R.string.order_summary_chocolate, boolToString(addChocolate));
        orderSummary += "\n" + getString(R.string.order_summary_quantity, quantity);
        orderSummary += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        orderSummary += "\n" + getString(R.string.thank_you);
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
            Toast.makeText(this, getString(R.string.not_more_than_100), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.not_less_than_1), Toast.LENGTH_SHORT).show();
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