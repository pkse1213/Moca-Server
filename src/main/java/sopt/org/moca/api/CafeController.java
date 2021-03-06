package sopt.org.moca.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.org.moca.service.CafeService;
import sopt.org.moca.utils.auth.JwtUtils;

import javax.servlet.http.HttpServletRequest;

import static sopt.org.moca.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("/cafe")
public class CafeController {
   private final CafeService cafeService;
    private static final String HEADER = "Authorization";



    public CafeController(final CafeService cafeService) {
        this.cafeService = cafeService;
    }


    /**
     *
     *1.11 스크랩 여부 추가
     * 인기 있는  검증 카페 리스트 조회
     *
     */
    @GetMapping("/pick/{length}")
    public ResponseEntity getEvaluatedCafeList(final HttpServletRequest httpServletRequest, @PathVariable final int length )
    {
        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */
        try{
            String user_id = JwtUtils.decode(httpServletRequest.getHeader(HEADER)).getUser_id();
            return new ResponseEntity<>(cafeService.findEvaluatedCafeSimpleList(length,user_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     *검증 카페 상세 정보 조회(카페 이름, 카페 주소, 총평)
     * @param httpServletRequest
     * @param cafe_id
     * @return
     */


    @GetMapping("/pick/{cafe_id}/detail")
    public ResponseEntity getEvaluatedCafeInfo(final HttpServletRequest httpServletRequest,@PathVariable final int cafe_id)
    {
        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */




        try{
            return new ResponseEntity<>(cafeService.findEvaluatedCafeInfo(cafe_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 검증카페 이미지 리스트 조회
     * @param httpServletRequest
     * @param cafe_id
     * @return
     */

    @GetMapping("/pick/{cafe_id}/image")
    public ResponseEntity getEvaluatedCafeImgList(final HttpServletRequest httpServletRequest,@PathVariable final int cafe_id)
    {
        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */




        try{
            return new ResponseEntity<>(cafeService.findEvaluatedCafeImg(cafe_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 검증 카페 인증 위원 평가 상세 조회
     * @param httpServletRequest
     * @param cafe_id
     * @return
     */

    @GetMapping("/pick/{cafe_id}/evaluate")
    public ResponseEntity getEvaluationList(final HttpServletRequest httpServletRequest,@PathVariable final int cafe_id)
    {
        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */




        try{
            return new ResponseEntity<>(cafeService.findEvaluationList(cafe_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }





    }


    /**
     * 검증카페 인증 위원 평가 상세 조회
     * @param httpServletRequest
     * @param cafe_id
     * @param barista_id
     * @return
     */

    @GetMapping("/pick/{cafe_id}/evaluate/barista/{barista_id}")
    public ResponseEntity getEvaluationDetail(final HttpServletRequest httpServletRequest,@PathVariable final int cafe_id,@PathVariable final int barista_id)
    {

        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */




        try{
            return new ResponseEntity<>(cafeService.findEvaluationDetail(cafe_id,barista_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 카페 상세 이미지 리스트 조회 ,핫플레이스에서도 사용(1/10)
     * @param httpServletRequest
     * @param cafe_id
     * @return
     */
    @GetMapping("/{cafe_id}/image")
    public ResponseEntity getCafeImgList(final HttpServletRequest httpServletRequest,@PathVariable final int cafe_id)
    {

        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */




        try{
            return new ResponseEntity<>(cafeService.findCafeImgList(cafe_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * 카페 상세 정보 조회
     * @param jwt
     * @param cafe_id
     * @return
     */
    @GetMapping("/{cafe_id}/detail")
    public ResponseEntity getCafeInfo(@RequestHeader("Authorization") final String jwt, @PathVariable final int cafe_id )
    {
        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */
        try{
        String user_id = JwtUtils.decode(jwt).getUser_id();
            return new ResponseEntity<>(cafeService.findCafeInfo(cafe_id,user_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }

    /**
     * 카페 시그니처 메뉴 리스트 조회
     * @param httpServletRequest
     * @param cafe_id
     * @return
     */

    @GetMapping("/{cafe_id}/signiture")
    public ResponseEntity getCafeSignitureList(final HttpServletRequest httpServletRequest, @PathVariable final int cafe_id)
    {

        /**
         * 토큰으로 유효한지 아닌지 확인 구현 필요
         *
         */



        try{
            return new ResponseEntity<>(cafeService.findCafeSignitureMenuList(cafe_id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






    /**
     * 인기 있는 카페 리스트 조회
     *
     * @param httpServletRequest
     * @param flag
     * @return
     */
    @GetMapping("/best/{flag}")
    public ResponseEntity getBestCafeList(final HttpServletRequest httpServletRequest, @PathVariable final int flag)
    {
        // flag 0: 스크랩 순
        // flag 1: 리뷰 개수 순

        try{
            return new ResponseEntity<>(cafeService.findBestCafeSimpleList(flag), HttpStatus.OK);

        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 핫플레이스로 카페 리스트
     * 1/10 다중이미지로 변경-> CafeByHotPlace 객체에 이미지 필드 삭제 및 cafe_img
     * 이미지는 cafe/{cafe_id}/image 로 이미지 받아 오면 됨
     * @param hot_place_id
     * @return
     */

    @GetMapping("/hot_place/{hot_place_id}")
    public ResponseEntity getCafeListByHotPlace(@PathVariable("hot_place_id") final int hot_place_id)
    {

        try{
            return new ResponseEntity<>(cafeService.findCafeByHotPlaceList(hot_place_id), HttpStatus.OK);

        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * 카페 랭킹
     * @param length
     * @return
     */
    @GetMapping("/ranking/{length}")
    public ResponseEntity getCafeListByRanking(@PathVariable("length") int length)
    {
        try{
            if(length == -1)
                length = 30;
            return new ResponseEntity<>(cafeService.findCafeByReviewRanking(length), HttpStatus.OK);

        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }






}
