package org.foree.bookreader.readpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.foree.bookreader.bean.ReadPageDataSet;
import org.foree.bookreader.bean.book.Chapter;

/**
 * Created by foree on 17-2-8.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    private Chapter chapter;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ReadFragment.newInstance(
                new ReadPageDataSet(
                        chapter.getChapterTitle(),
                        chapter.getPage(position),
                        chapter != null ? chapter.numberOfPages() : 0,
                        position + 1)
        );
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return chapter != null ? chapter.numberOfPages() : 0;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
        notifyDataSetChanged();
    }

}