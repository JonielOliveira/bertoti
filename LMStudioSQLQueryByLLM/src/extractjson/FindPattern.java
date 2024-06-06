
package extractjson;

import java.util.ArrayList;

public class FindPattern {
    
    public static int conjugatesQuantity(String text, String startChar, String endChar){
        int contStartChar = 0;
        int contEndChar = 0;
        
        int i = 0;
        while(i != -1){
            i = text.indexOf(startChar,i);
            if(i >= 0){
                contStartChar++;
                i++;
            }
        }
        
        i = 0;
        while(i != -1){
            i = text.indexOf(endChar,i);
            if(i >= 0){
                contEndChar++;
                i++;
            }
        }
        if(contStartChar == contEndChar){
            return contStartChar;
        }
        else if(contStartChar > contEndChar){
            return -1;
        }
        else{
            return -2;
        }
    }
    
    public static int[][] positionsMap(String text, String startChar, String endChar){
        int nConjugate = FindPattern.conjugatesQuantity(text, startChar, endChar);
        if(nConjugate > 0){
            int[][] listStart = new int[nConjugate][2];
            int[][] listEnd = new int[nConjugate][2];
            int[][] listFull = new int[2 * nConjugate][3];
            
            int i = 0;
            int j = 0;
            while(i != -1){
                i = text.indexOf(startChar,i);
                if(i >= 0){
                    listStart[j][0] = i;
                    listStart[j][1] = 1;
                    i++;
                    j++;
                }
            }
            
            i = 0;
            j = 0;
            while(i != -1){
                i = text.indexOf(endChar,i);
                if(i >= 0){
                    listEnd[j][0] = i;
                    listEnd[j][1] = -1;
                    i++;
                    j++;
                }             
            }
            
            i = 0;
            j = 0;
            for(int k = 0; k < 2 * nConjugate; k++){
                
                if(i < nConjugate && j < nConjugate){
                    if(listStart[i][0] < listEnd[j][0]){
                        listFull[k][0] = listStart[i][0];
                        listFull[k][1] = 1;
                        i++;
                    }
                    else{
                        listFull[k][0] = listEnd[j][0];
                        listFull[k][1] = -1;
                        j++;
                    }
                }
                else if(j < nConjugate){
                    listFull[k][0] = listEnd[j][0];
                    listFull[k][1] = -1;
                    j++;
                }
                else{
                    listFull[k][0] = listStart[i][0];
                    listFull[k][1] = 1;
                    i++;
                }
            }
            
            int sum = 0;
            for(int k = 0; k < 2 * nConjugate; k++){          
                if(listFull[k][1] == 1){
                    sum++;
                    listFull[k][2] = sum;
                }
                else if(listFull[k][1] == -1){
                    listFull[k][2] = sum; 
                    sum--;                   
                }
            }
            return listFull;
        }
        else{
            return null;
        }
                       
    }
    
    public static int conjugatesQuantityFilter(String text, String startChar, String endChar, int level){
        int n = FindPattern.conjugatesQuantity(text, startChar, endChar);
        
        if(n > 0){
            int listFull[][] = FindPattern.positionsMap(text, startChar, endChar);
            int j = 0;
            for(int i = 0; i < 2 * n; i++){
                if(listFull[i][2] == level){
                    j++;
                }
            }
            if(j % 2 == 0){
                return j/2;
            }
            else{
                return 0;
            }
        }
        else{
            return n;
        }
       
    }
    
    public static int[][] positionsMapFilter(String text, String startChar, String endChar, int level){
        int n = FindPattern.conjugatesQuantityFilter(text, startChar, endChar, level);
        
        if(n > 0){
            int m = FindPattern.conjugatesQuantity(text, startChar, endChar);
            int listFull[][] = FindPattern.positionsMap(text, startChar, endChar);
            int listFilter[][] = new int[2 * n][3];

            int j = 0;
            for(int i = 0; i < 2 * m; i++){
                if(listFull[i][2] == level){
                    listFilter[j][0] = listFull[i][0];
                    listFilter[j][1] = listFull[i][1];
                    listFilter[j][2] = listFull[i][2];
                    j++;
                }
            }
            return listFilter;
        }
        else{
            return null;
        }    
    }
    
    public static int[][] openClosedFlags(String text, String startChar, String endChar, int level){
        int n = FindPattern.conjugatesQuantityFilter(text, startChar, endChar, level);
        if(n > 0){
            int flags[][] = new int[n][2];
            int vetor[][] = FindPattern.positionsMapFilter(text, startChar, endChar, level);
            
            for(int i = 0; i < n; i++){
                flags[i][0] = vetor[i * 2][0];
                flags[i][1] = vetor[i * 2 + 1][0];
            }
            return flags; 
        }
        else{
            return null;
        }
    }
    
    public static ArrayList<String> extractList(String text, String startChar, String endChar, int level){
        int n = FindPattern.conjugatesQuantityFilter(text, startChar, endChar, level);
        if(n > 0){
            ArrayList<String> list = new ArrayList<>();
            int flags[][] =  FindPattern.openClosedFlags(text, startChar, endChar, level);
            
            for(int i = 0; i < n; i++){
                list.add(text.substring(flags[i][0], flags[i][1] + 1));
            }
            
            return list;
        }
        else{
            return null;
        }
          
    }  
        
}
