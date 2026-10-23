package com.example.SafarNow.Dto.Response;

import com.example.SafarNow.Enum.TripStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {

    String pickUp;

    String destination;

    double tripDistanceInKm;

    TripStatus tripStatus;

    double billAmount;

    Date bookedAt;

    Date lastUpdateAt;

    CustomerResponse customerResponse;

    CabResponse cabResponse;
}
