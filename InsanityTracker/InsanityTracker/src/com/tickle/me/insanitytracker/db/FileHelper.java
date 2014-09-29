package com.tickle.me.insanitytracker.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {
	
	
   public static String[] parseSqlFile(InputStream sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new InputStreamReader(sqlFile)));
    }

 public static String[] parseSqlFile(BufferedReader sqlFile) throws IOException {
        String line;
        StringBuilder sql = new StringBuilder();
        String multiLineComment = null;

        while ((line = sqlFile.readLine()) != null) {
            line = line.trim();

            // Check for start of multi-line comment
if (multiLineComment == null) {
    // Check for first multi-line comment type
if (line.startsWith("/*")) {
if (!line.endsWith("}")) {
multiLineComment = "/*";
}
// Check for second multi-line comment type
} else if (line.startsWith("{")) {
if (!line.endsWith("}")) {
multiLineComment = "{";
}
// Append line if line is not empty or a single line comment
} else if (!line.startsWith("--") && !line.equals("")) {
    sql.append(line);
} // Check for matching end comment
} else if (multiLineComment.equals("/*")) {
if (line.endsWith("*/")) {
    multiLineComment = null;
}
// Check for matching end comment
} else if (multiLineComment.equals("{")) {
if (line.endsWith("}")) {
            multiLineComment = null;
        }
    }

}

sqlFile.close();

return sql.toString().split(";");
	    }

}