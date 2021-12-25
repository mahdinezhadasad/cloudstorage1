package com.udacity.jwdnd.course1.cloudstorage.Mapper;


import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {


    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int addNote(Note note);


    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllNotes(Integer userId);


    //@Delete("DELETE * FROM NOTES WHERE noteid = #{noteId}")
    //int deleteNote(Integer id);

   // @Update("UPDATE NOTES SET notetitle={#noteTitle},notedescription = {#noteDescription}")
   // int updateNote(Note note);

    @Delete("delete from Notes where noteid = #{noteid}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer deleteById(Integer id);



    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} " +
            "WHERE noteid = #{noteId}")
    int updateNoteById(Note note);


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note noteById(Integer id);

    @Select("SELECT key FROM NOTES WHERE noteid = #{noteid}")

    String noteKeyById(Note note);

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNoteByTitle(String noteTitle);


}
