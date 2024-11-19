package com.sinhvien.orderdrinkapp.CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.orderdrinkapp.DTO.MonDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.List;

public class AdapterDisplayMenu extends BaseAdapter {

    Context context;
    int layout;
    List<MonDTO> monDTOList;
    ViewHolder viewHolder; // Corrected ViewHolder naming convention


    public AdapterDisplayMenu(Context context, int layout, List<MonDTO> monDTOList) {
        this.context = context;
        this.layout = layout;
        this.monDTOList = monDTOList;
    }

    @Override
    public int getCount() {
        return monDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return monDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monDTOList.get(position).getMaMon();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            viewHolder = new ViewHolder();  // Corrected ViewHolder naming
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            viewHolder.img_custommenu_HinhMon = view.findViewById(R.id.img_custommenu_HinhMon);
            viewHolder.txt_custommenu_TenMon = view.findViewById(R.id.txt_custommenu_TenMon);
            viewHolder.txt_custommenu_TinhTrang = view.findViewById(R.id.txt_custommenu_TinhTrang);
            viewHolder.txt_custommenu_GiaTien = view.findViewById(R.id.txt_custommenu_GiaTien);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag(); // Corrected ViewHolder naming
        }

        MonDTO monDTO = monDTOList.get(position);
        viewHolder.txt_custommenu_TenMon.setText(monDTO.getTenMon());
        viewHolder.txt_custommenu_GiaTien.setText(monDTO.getGiaTien() + " VNĐ");

        // Hiển thị tình trạng của món (Set visibility based on data)
        if (monDTO.getTinhTrang().equals("true")) {
            viewHolder.txt_custommenu_TinhTrang.setVisibility(View.VISIBLE);
            viewHolder.txt_custommenu_TinhTrang.setText("Còn món");
        } else {
            viewHolder.txt_custommenu_TinhTrang.setVisibility(View.VISIBLE); // Or View.GONE to hide
            viewHolder.txt_custommenu_TinhTrang.setText("Hết món");
        }


        // Lấy hình ảnh
        if (monDTO.getHinhAnh() != null) {
            byte[] menuimage = monDTO.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(menuimage, 0, menuimage.length);
            viewHolder.img_custommenu_HinhMon.setImageBitmap(bitmap);
        } else {
            viewHolder.img_custommenu_HinhMon.setImageResource(R.drawable.cafe_americano);
        }

        return view;
    }


    // Tạo ViewHolder lưu trữ component (Corrected class name)
    public class ViewHolder {  // Corrected class name to ViewHolder
        ImageView img_custommenu_HinhMon;
        TextView txt_custommenu_TenMon, txt_custommenu_GiaTien, txt_custommenu_TinhTrang;
    }
}