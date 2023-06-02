package com.kosa.theBeauty.reservation.dao;

import java.util.List;

import com.kosa.theBeauty.product.domain.BrandVO;
import com.kosa.theBeauty.reservation.domain.ProductVO;
import com.kosa.theBeauty.reservation.domain.ReservationVO;

public interface reserveDAO {
	
	BrandVO selectBrandInfo(BrandVO vo);
	ProductVO selectProductInfo(BrandVO vo);
	int insertReservationInfo(ReservationVO vo);
	String selectReservationInfo(ReservationVO reservationvo);
	List<ReservationVO> selectReservationforDetail(ReservationVO reservationvo);
	int updateReservation(ReservationVO reservationvo);
}
