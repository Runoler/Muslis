package com.example.muslis.dtos;

import com.example.muslis.enums.Role;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Listener;
import com.example.muslis.models.UserInfo;
import lombok.Data;

@Data
public class UserInfoDTO {

    private Long id;
    private String username;
    private String email;
    private Role userRole;
    private Long artistPart;
    private Long listenerPart;

    public static UserInfoDTO fromModel(UserInfo userInfo) {
        UserInfoDTO dto = new UserInfoDTO();

        dto.setId(userInfo.getId());
        dto.setUsername(userInfo.getUsername());
        dto.setEmail(userInfo.getEmail());
        dto.setArtistPart(userInfo.getArtistPart().getId());
        dto.setListenerPart(userInfo.getListenerPart().getId());

        return dto;
    }
}
