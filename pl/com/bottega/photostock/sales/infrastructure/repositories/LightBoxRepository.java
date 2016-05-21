package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;

/**
 * Created by bartosz.paszkowski on 21.04.2016.
 */
public interface LightBoxRepository {
    public LightBox load(String nr);
    public void save(LightBox lbx);
}
