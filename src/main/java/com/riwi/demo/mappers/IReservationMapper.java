    package com.riwi.demo.mappers;

    

    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.MappingConstants;
    import org.mapstruct.Named;

    import com.riwi.demo.api.request.ReservationRequest;
    import com.riwi.demo.api.response.ReservationResponse;
    import com.riwi.demo.domain.entities.Reservation;
    import com.riwi.demo.domain.entities.UserEntity;

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface IReservationMapper {


        @Mapping(target = "id", ignore = true)
        @Mapping(source = "books", target = "books", ignore = true)
        @Mapping(source = "user", target = "user", qualifiedByName = "mapIdToEntity")
        Reservation requestToEntity(ReservationRequest request);

        @Mapping(target = "books", ignore = true)
        ReservationResponse entityToResponse(Reservation entity);


        


        @Named("mapIdToEntity")
        default UserEntity mapIdToEntity(Long id){
            if (id == null) return null;

            UserEntity user = new UserEntity();
            user.setId(id);
            return user;
            
        }

    }
