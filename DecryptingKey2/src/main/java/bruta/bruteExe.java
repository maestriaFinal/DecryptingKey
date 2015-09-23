/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bruta;

import ejemplo.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author maveces
 */
public class bruteExe {
    public static void main (String []args){
    
     brute search = new brute(DigestUtils.md5Hex("aaa"));
    search.setLength(5).calc().show();
    }
}
