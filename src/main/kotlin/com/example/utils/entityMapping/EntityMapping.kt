package com.example.utils.entityMapping

import com.example.entitys.AdminEntity
import com.example.entitys.PlayListEntity
import com.example.entitys.SongEntity
import com.example.entitys.UserEntity
import com.example.models.AdminLogin
import com.example.models.Playlist
import com.example.models.Song

fun entityToSong(row: SongEntity): Song {
    return Song(row.songTitle,row.songArtist,row.songDuration)
}
fun entityToSongId(row:SongEntity):Int{
    return row.id.value
}
fun entityToId(row:PlayListEntity):Int{
    return row.id.value
}
fun entityToAdmin(row:AdminEntity):AdminLogin{
    return AdminLogin(row.adminName,row.password)
}
fun entityToUserId(row:UserEntity?):Int?{
    return row?.id?.value
}
fun entityToPlayList(row:PlayListEntity?):String?{
    return row?.playListName
}
fun entityToPlay(row:PlayListEntity?):Playlist?{
    return if (row != null) {
        Playlist(row.id.value,row.userId,row.songId)
    }
    else null
}
