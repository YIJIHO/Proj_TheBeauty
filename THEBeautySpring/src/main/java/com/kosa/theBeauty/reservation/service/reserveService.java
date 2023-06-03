package com.kosa.theBeauty.reservation.service;

import java.util.List;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import com.kosa.theBeauty.product.domain.BrandVO;
import com.kosa.theBeauty.reservation.dao.reserveDAO;
import com.kosa.theBeauty.reservation.domain.ReservationVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class reserveService {

	private final reserveDAO dao;
	public BrandVO getBrandInfo(BrandVO vo) {
		try {
			return dao.selectBrandInfo(vo);
		} catch (Exception e) {
			return null;
		}
	}


	public void setReservation(ReservationVO vo) throws UncategorizedSQLException {
		dao.insertReservationInfo(vo);
	}
	
	public boolean cancelReservation(ReservationVO reservationvo) {
	      try {
	         if(dao.updateReservation(reservationvo)==1) {
	            return true;
	         }
	         return false;
	      } catch (Exception e) {
	         return false;
	      }
	  }

	public boolean getSchedule(ReservationVO reservationvo) {
		try {
			if (dao.selectReservationInfo(reservationvo).equals("예약확정")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public ReservationVO getReservation(int userSeq) {
		return dao.selectReservationByUser(userSeq);
	}
	
	public List<ReservationVO> getReservation(ReservationVO reservationvo) {
	      return dao.selectReservationforDetail(reservationvo);
	   }


	public List<ReservationVO> getBrandReservation(int brandSeq) {
		return dao.selectReservationBrand(brandSeq);
	}


	public ReservationVO getReservationBySeq(int reservationSeq) {
		return dao.selectReservationBySeq(reservationSeq);
	}

}
