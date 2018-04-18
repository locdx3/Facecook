package vn.com.codedao.facecook.view.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.menu.MMenu;
import vn.com.codedao.facecook.view.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    List<MMenu> mMenuList;
    IOnClickItemMenu iOnClickItemMenu;

    public MenuAdapter(List<MMenu> mMenuList, IOnClickItemMenu iOnClickItemMenu) {
        this.mMenuList = mMenuList;
        this.iOnClickItemMenu = iOnClickItemMenu;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        MMenu mMenu = mMenuList.get(position);
        holder.txtConten.setText(mMenu.getmName());
        holder.txtConten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickItemMenu.onClickItemMenu(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuList == null ? 0 : mMenuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imgIcon;
        public TextView txtConten;

        public MenuViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtConten = itemView.findViewById(R.id.txtItem);
        }
    }
}
