package com.vizier.constants;

import java.io.File;

public class VizierAgentConstants {
    public static String directoryPath = "."+ File.separator+"cell_content";
    public static String stubsDirectoryPath = directoryPath+File.separator+"stubs";
    public static String iniFilePath = directoryPath+File.separator+"mypy.ini";
    public static String iniOriginalFilePath = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"mypy.ini";
}
