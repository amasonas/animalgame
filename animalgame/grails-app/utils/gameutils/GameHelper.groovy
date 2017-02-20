
package gameutils


class GameHelper {
    
    static def convertStringListToLongList(String listStr) {
        List<Long> listIdLong=[] 
        if(listStr){
            if(listStr?.length() == 0)
            return listIdLong
            String [] listIdStr = listStr.split(";")
            for( String idString : listIdStr )
            listIdLong.add(Long.parseLong(idString));
            return listIdLong;
        }else{
            return listIdLong;
        }
    } 
}

