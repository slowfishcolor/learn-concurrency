package com.sfc.learnconcurrency.book.chapter3;
import com.sfc.learnconcurrency.annotations.Immutable;
import com.sfc.learnconcurrency.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用可变对象 set 来管理不可变对象 ImmutableThreeStooges 的状态
 * 状态构建封闭在了构造过程中，构造完成后状态就不会改变，是不可变的
 * isStooge 也就是线程安全的
 */
@Immutable
@ThreadSafe
public class ImmutableThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ImmutableThreeStooges() {
        stooges.add("Prophet");
        stooges.add("Penny");
        stooges.add("Joe");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
