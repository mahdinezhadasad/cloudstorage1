package com.udacity.jwdnd.course1.cloudstorage.Mapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAllFiles(Integer userId);
    @Insert("INSERT INTO FILES (filename ,contenttype,filesize,userid,filedata) VALUES(#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true,keyProperty = "fileId")
    int addFile(File file);
    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int deleteFile(Integer userId);
    @Update("UPDATE FILES SET fileId = #{fileId}, filedata = #{fileData}")
    int updateFile(File file);
    @Update("UPDATE FILES SET fileId = #{fileId}, filedata = #{fileData} WHERE fileid = #{fileId}")
    int updateFileByefileId(File file);
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);
    @Select("SELECT * FROM FILES")
    Set<File> getFiles();
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    Set<File> getFilesById(Integer userid);
    @Select("SELECT * FROM FILES WHERE filename = #{fileName} AND userid = #{userId}")
    File getFileByNameAndUserId(String fileName, int userId);
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByName(String fileName);

}