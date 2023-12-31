package com.sp.base.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.reactivex.disposables.Disposable;


public class DisposableList {

    private final List<Disposable> disposables = Collections.synchronizedList(new ArrayList<>());

    public void add(Disposable d) {
        disposables.add(d);
    }

    public void remove(Disposable d) {
        disposables.remove(d);
    }

    public void dispose() {
        synchronized (disposables) {
            Iterator<Disposable> iterator = disposables.iterator();
            while (iterator.hasNext()) {
                iterator.next().dispose();
            }
            disposables.clear();
        }
    }

}
