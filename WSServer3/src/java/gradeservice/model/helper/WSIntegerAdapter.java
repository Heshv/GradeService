/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.model.helper;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author rsawoniewski
 */
public class WSIntegerAdapter extends XmlAdapter<String, Integer> {

    @Override
    public Integer unmarshal(String v) throws Exception {
        return Integer.parseInt(v);
    }

    @Override
    public String marshal(Integer v) throws Exception {
        if(v==null) return "null";
        return v.toString();
    }


}
