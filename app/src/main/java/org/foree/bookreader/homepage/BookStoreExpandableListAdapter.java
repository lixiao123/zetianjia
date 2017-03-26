package org.foree.bookreader.homepage;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.foree.bookreader.R;
import org.foree.bookreader.base.BaseApplication;
import org.foree.bookreader.bean.book.Book;

import java.util.List;

/**
 * Created by foree on 17-3-11.
 */

public class BookStoreExpandableListAdapter implements ExpandableListAdapter {
    private List<List<Book>> bookStoreList;
    private Context mContext;

    public BookStoreExpandableListAdapter(Context context, List<List<Book>> bookStoreList) {
        mContext = context;
        this.bookStoreList = bookStoreList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return bookStoreList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return bookStoreList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bookStoreList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bookStoreList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_book_store_category, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.textView.setText(bookStoreList.get(groupPosition).get(0).getCategory());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_book_store_item, parent, false);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        Book childBook = bookStoreList.get(groupPosition).get(childPosition);
        childViewHolder.bookName.setText(childBook.getBookName());


        if (!childBook.getBookCoverUrl().isEmpty()) {
            ImageLoader.getInstance().displayImage(childBook.getBookCoverUrl(), childViewHolder.bookCover,
                    BaseApplication.getInstance().getDisplayImageOptions());
            childViewHolder.bookCover.setVisibility(View.VISIBLE);

        } else {
            childViewHolder.bookCover.setVisibility(View.GONE);
        }

        if (!childBook.getDescription().isEmpty()) {
            childViewHolder.description.setText(childBook.getDescription());
            childViewHolder.description.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.description.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private static class GroupViewHolder {
        private TextView textView;

        GroupViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.book_store_category_title);
        }
    }

    private static class ChildViewHolder {
        private TextView bookName;
        private ImageView bookCover;
        private TextView description;

        ChildViewHolder(View view) {
            bookName = (TextView) view.findViewById(R.id.book_store_item_name);
            bookCover = (ImageView) view.findViewById(R.id.book_store_item_cover);
            description = (TextView) view.findViewById(R.id.book_store_item_description);
        }
    }
}