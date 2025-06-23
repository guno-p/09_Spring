package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 입장 알림 메시지 데이터 클래스
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreetingMessage {

    /**
     * 입장하는 사용자의 이름
     * - 연결 시 클라이언트에서 입력한 사용자 이름
     * - "OOO님이 입장했습니다" 형태의 알림 메시지 생성에 사용
     */
    private String name;
}