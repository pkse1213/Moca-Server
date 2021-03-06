package sopt.org.moca.api;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.org.moca.model.CouponAuthReq;
import sopt.org.moca.model.CouponReq;
import sopt.org.moca.model.DefaultRes;
import sopt.org.moca.model.MembershipReq;
import sopt.org.moca.service.MembershipService;
import sopt.org.moca.utils.ResponseMessage;
import sopt.org.moca.utils.StatusCode;
import sopt.org.moca.utils.auth.Auth;
import sopt.org.moca.utils.auth.JwtUtils;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static sopt.org.moca.model.DefaultRes.FAIL_DEFAULT_RES;
import static sopt.org.moca.model.DefaultRes.UNAUTHORIZED_RES;

@Slf4j
@RestController
public class MembershipController {
    private  final MembershipService membershipService;


    public MembershipController(final MembershipService membershipService) {
        this.membershipService = membershipService;
    }


    /**
     * 멤버쉽 리스트 조회
     * @param jwt
     * @return
     */
    @Auth
    @GetMapping("/membership")
    public ResponseEntity findMembershipList(@RequestHeader("Authorization") final String jwt)
    {
        String user_id = null;

        try{
            user_id = JwtUtils.decode(jwt).getUser_id();
            return new ResponseEntity<>(membershipService.findMembershipList(user_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 멤버쉽 적립
     * @param membershipReq
     * @return
     */
    @PostMapping("/membership")
    public ResponseEntity saveMembership(@RequestBody final MembershipReq membershipReq)
    {

        try{
            return new ResponseEntity<>(membershipService.saveMembership(membershipReq), HttpStatus.OK);
        }
        catch (Exception e){
            log.error(e.getMessage());

            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * coupon리스트 조회
     * @param jwt
     * @return
     */
    @Auth
    @GetMapping("/coupon")
    public ResponseEntity findCouponList(@RequestHeader("Authorization") final String jwt)
    {
        String user_id = null;
        try{
            user_id = JwtUtils.decode(jwt).getUser_id();
            return new ResponseEntity<>(membershipService.findCouponList(user_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 쿠폰 사용
     * @param couponReq
     * @return
     */
    @PostMapping("/coupon")
    public ResponseEntity useCoupon(@RequestBody final CouponReq couponReq)
    {
        try{
            log.info(couponReq.toString());
            return new ResponseEntity<>(membershipService.useCoupon(couponReq), HttpStatus.OK);
        } catch (Exception e){

            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * 쿠폰 인증 테이블 등록
     * @param couponAuthReq
     */
    @PostMapping("/coupon/auth")
    public ResponseEntity registerAuth(@RequestBody final CouponAuthReq couponAuthReq)
    {
        try{
            return new ResponseEntity<>(membershipService.registerAuth(couponAuthReq.getCoupon_id()), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     *  쿠폰 인증 테이블 삭제
     * @param couponAuthReq
     */
    @DeleteMapping("/coupon/auth")
    public ResponseEntity deleteAuth(@RequestBody final CouponAuthReq couponAuthReq)
    {

        try{
            return new ResponseEntity<>(membershipService.deleteAuth(couponAuthReq.getCoupon_id()), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * 멤버십 사용 내역 조회
     *
     */
    @GetMapping("/membership/history")
    public ResponseEntity findHistoryOfMembershipList(@RequestHeader("Authorization") final String jwt)
    {

        String user_id = null;
        try{
            user_id = JwtUtils.decode(jwt).getUser_id();
            return new ResponseEntity<>(membershipService.findHistoryOfMembership(user_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }


}
