package com.hoangtien2k3.foody_order_app.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.activity.ActivityImpl.HomeActivity;
import com.hoangtien2k3.foody_order_app.model.Notify;
import com.hoangtien2k3.foody_order_app.model.NotifyToUser;
import com.hoangtien2k3.foody_order_app.model.Order;

public class OrderCard extends LinearLayout {
    private Order order;

    public OrderCard(Context context){
        super(context);
    }

    public OrderCard(Context context, Order order) {
        super(context);
        this.order = order;
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_order_card, this);

        TextView tvDate = findViewById(R.id.tvDateMakeOrder);
        TextView tvPrice = findViewById(R.id.tvOrderPrice);
        TextView tvAddress = findViewById(R.id.tvOrderAddress);
        TextView tvStatus = findViewById(R.id.tvOrderStatus);

        Button btnConfirm = findViewById(R.id.btnConfirmOrder);
        btnConfirm.setOnClickListener(view -> {
            order.setStatus("Delivered");
            HomeActivity.dao.updateOrder(order);
            Toast.makeText(context, "Đã cập nhật lại trạng thái!", Toast.LENGTH_SHORT).show();

            // System Notify
            int quantityOrder = HomeActivity.dao.quantityOfOrder();
            HomeActivity.dao.addNotify(new Notify(1, "Cập nhật thông tin ứng dụng!",
                    "Ứng dụng đã có " + quantityOrder + " đơn đặt hàng!", HomeActivity.dao.getDate()));

            // User Notify
            String content = "Đơn hàng ngày " + order.getDateOfOrder() + " đã được nhận!\nCảm ơn bạn đã tin tưởng ứng dụng!";
            HomeActivity.dao.addNotify(new Notify(1, "Thông báo về đơn hàng!",
                    content, HomeActivity.dao.getDate()));
            HomeActivity.dao.addNotifyToUser(new NotifyToUser(
                    HomeActivity.dao.getNewestNotifyId(), HomeActivity.user.getId()));
        });

        if(order.getStatus().equals("Delivered")){
            btnConfirm.setEnabled(false);
        }

        if(order.getStatus().equals("Canceled")){
            btnConfirm.setText("ĐÃ HỦY ĐƠN");
            btnConfirm.setEnabled(false);
        }

        tvDate.setText(order.getDateOfOrder());
        tvAddress.setText(order.getAddress());
        tvPrice.setText(getRoundPrice(order.getTotalValue()));
        tvStatus.setText(order.getStatus());
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}
