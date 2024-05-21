package com.wangtian.string;

/**
 * 用java语言实现两个函数encode()和decode()，
 * 分别实现对字符串的变换和复原。变换函数encode()顺序考察已知字符串的字符，按以下规则逐组生成新字符串：
 * <p>
 * （1）	若已知字符串的当前字符不是大于0的数字字符，则复制该字符于新字符串中。
 * （2）	若已知字符串的当前字符是一个数字字符，且它之后没有后继字符，则简单地将它复制到新字符串中。
 * （3）	若已知字符串的当前字符是一个大于0的数字字符，并且还有后继字符，设该数字字符的面值为n，
 * 则将它的后继字符（包括后继字符是一个数字字符）重复复制n+1次到新字符串中。
 * （4）	若已知字符串的当前字符是下划线‘_’，则将当前字符串变换为用‘\UL’。
 * （5）	以上述一次变换为一组，在不同组之间另插入一个下划线‘_’用于分隔。
 * <p>
 * 例如：encode()函数对字符串24ab_2t2的变换结果为
 * 444_aaaaa_a_b_\UL_ttt_t_2
 * <p>
 * 复原函数decode()做变换函数encode()的相反的工作，按照上述规则逆运算，变回原来的字符串。滤掉多余的下划线字符。
 * <p>
 * 要求：代码可读性要好，逻辑清楚。
 *
 * @author wang1
 */
public class Demo {


    /**
     * 复原函数decode()做变换函数encode()的相反的工作，按照上述规则逆运算，变回原来的字符串。滤掉多余的下划线字符。
     *
     * @param str
     */
    public static String decode(String str) {
        //创建一个StringBuffer用于字符串截取
        StringBuffer buff = new StringBuffer();
        //把所有的_进行切割
        String[] arr = str.split("_");
        for (int i = 0; i < arr.length; i++) {
                //判断是否是\UL是的话替换为_
                if ("\\UL".equals(arr[i])) {
                    buff.append("_");
                }
                //判断切割的字符长度是否大于1如果大于则字符串长度-1
                else if (arr[i].length() > 1) {
                    buff.append(arr[i].length() - 1);
                } else {
                    //不大于则用本来的字符串
                    buff.append(arr[i]);
                }
            }
        return buff.toString();
    }

    /**
     * encode()函数对字符串24ab_2t2的变换结果为
     * * 444_aaaaa_a_b_\UL_ttt_t_2
     *
     * @param str
     */
    public static String encode(String str) {
        String aloneString = "";

        for (int i = 0; i < str.length(); i++) {
            // 若以知字符串中包含有下划线'_'，则变换为用"/UL"
            if (str.charAt(i) == '_') {
                aloneString += "\\UL";
            }
            // 字符串的当前字符不是大于0的数字字符,复制该字符与新字符串中
            else if ("123456789".indexOf(str.charAt(i), 0) == -1) {
                aloneString += str.charAt(i);
                // 拼接最后一位
            } else if ("0123456789".indexOf(str.charAt(i), 0) != -1 && i == (str.length() - 1)) {
                aloneString += str.charAt(i);
            }
            // 字符串的当前字符是一个大于0的数字字符，并且还有后继字符,
            else if ("0123456789".indexOf(str.charAt(i), 0) != -1 && i != str.length() - 1) {
                int pool = Integer.parseInt(str.charAt(i) + "");

                for (int j = 0; j <= pool; j++) {
                    aloneString += str.charAt(i + 1);
                }
            }
            aloneString += "_";
        }
        //切割掉最后一个_
        aloneString = aloneString.substring(0, aloneString.length() - 1);
        return aloneString;
    }

    public static void main(String[] args) {
        /**
         * 初始传入的字符串
         */
        String str = "24ab_2t2";
        //改变后的字符串
        String changeString = encode(str);
//        //回归后的字符串
        String initialString = decode(changeString);
        //改变
        System.out.println(changeString);
        //回归
        System.out.println(initialString);
    }


}
