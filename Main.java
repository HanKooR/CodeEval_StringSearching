package codeeval_StringSearching;
/*
 * Copyright (C) 2015 hankoor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 Description:
 You are given two strings. Determine if the second string is a substring of the
 first (Do NOT use any substr type library function). The second string may
 contain an asterisk(*) which should be treated as a regular expression i.e. 
 matches zero or more characters. The asterisk can be escaped by a \ char in 
 which case it should be interpreted as a regular '*' character. To summarize: 
 the strings can contain alphabets, numbers, * and \ characters.
 Input sample:
 File containing two comma delimited strings per line. e.g. 
 Hello,ell
 This is good, is 
 CodeEval,C*Eval
 Old,Young
 Output sample:
 If the second string is indeed a substring of the first, print out a 
 'true'(lowercase), else print out a 'false'(lowercase), one per line.

 e.g.
 true
 true
 true
 false
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static void loadFile(String fileName) {
        File file = new File(fileName);
        if (!file.canRead() || !file.isFile()) {
            System.exit(0);
        }
        BufferedReader in = null;
        String row = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            while ((row = in.readLine()) != null) {
                String firstStr = row.split(",")[0];
                String secondStr = row.split(",")[1];
                /*
                Search row for \*:
                 Don't interprete as asterisk
                 */
                if (secondStr.contains("\\*")) {
                    firstStr = firstStr.replaceAll("\\*", "U");
                    secondStr = secondStr.replaceAll("\\\\\\*", "U");
                }
                secondStr = secondStr.replaceAll("\\*", ".*");
                secondStr = ".*" + secondStr + ".*";
                System.out.println(firstStr.matches(secondStr));
            }

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] pathToFile) {
        if ((pathToFile != null) && (pathToFile.length == 1) && (!pathToFile[0].isEmpty())) {
            loadFile(pathToFile[0]);
        }
    }
}
