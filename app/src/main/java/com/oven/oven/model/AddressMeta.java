package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2018-01-29.
 */

public class AddressMeta {
    private boolean is_end;
    private int total_count, pageable_count;


    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getPageable_count() {
        return pageable_count;
    }

    public void setPageable_count(int pageable_count) {
        this.pageable_count = pageable_count;
    }
}
