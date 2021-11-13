package com.example.opt_os;

public class OPT {
    private int fault ;


    public OPT() {
    }

    public String run(int frames, int len, int [] array) {
        int pointer = 0, fault = 0;
        boolean isFull = false;
        int buffer[];
        int result[][];
        result = new int[len][frames+1];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
            buffer[j] = -1;
        for (int i = 0; i < len; i++) {
            int find = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == array[i])//kiem tra i cos trong buffer chua
                {
                    find = j;
                    result[i][frames]=-2;//danh dau khong loi
                    break;
                }
            }
            if (find == -1)//neu k tim thay
            {
                if (isFull) //kiem tra day chua
                {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];
                    for (int j = i + 1; j < len; j++) {
                        for (int k = 0; k < frames; k++) {
                            if ((array[j] == buffer[k]) && (index_flag[k] == false)) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if (max == 0)
                        max = 200;
                    for (int j = 0; j < frames; j++) {
                        if (index[j] == 0)
                            index[j] = 200;
                        if (index[j] > max) {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = array[i];
                fault++;
                if (!isFull) {
                    pointer++;
                    if (pointer == frames) {
                        pointer = 0;
                        isFull = true;
                    }
                }
            }
            for (int j = 0; j < frames; j++)
                result[i][j] = buffer[j];
        }
        String res = "Optimal page replacement : \n";
        for (int i = 0; i <=frames; i++) {
            for (int j = 0; j < len; j++){
                if(result[j][i]==-1){
                    res= res.concat("_"+"\t");
                }else if(result[j][i]==-2&&i==frames) {
                    res = res.concat(" " + "\t");
                }else if(result[j][i]!=-2&&i==frames) {
                    res = res.concat("*" + "\t");
                }
                else
                res= res.concat(Integer.toString(result[j][i])+"\t");
            }
            res=res.concat("\n");
        }

        res = res.concat("Số Trang Lỗi: " + fault+"\n");
        this.fault=fault;
        return res;
    }

}
