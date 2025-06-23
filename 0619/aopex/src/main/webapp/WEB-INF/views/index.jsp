<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STOMP WebSocket 채팅 애플리케이션</title>

    <!-- Bootstrap CSS for UI Styling -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- STOMP.js 라이브러리: WebSocket 위에서 STOMP 프로토콜 사용 -->
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>

    <style>
        /* 추가 스타일링 */
        .chat-container {
            max-height: 400px;
            overflow-y: auto;
            border: 1px solid #ddd;
            padding: 10px;
            margin-top: 15px;
        }

        .info-message {
            font-style: italic;
            color: #777;
        }

        .message-time {
            font-size: 0.8em;
            margin-right: 5px;
        }

        .connection-status {
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 페이지 헤더 -->
    <div class="page-header">
        <h1>🚀 Spring WebSocket + STOMP 프로토콜을 이용한 실시간 채팅</h1>
    </div>

    <!-- 연결 및 메시지 전송 컨트롤 영역 -->
    <div class="row">
        <!-- 연결 섹션 -->
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">🔌 WebSocket 연결</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="name">사용자 이름:</label>
                            <input type="text"
                                   id="name"
                                   class="form-control"
                                   placeholder="이름을 입력하세요 (최대 20자)"
                                   maxlength="20"
                                   style="margin-left: 5px; margin-right: 10px;">
                        </div>

                        <!-- 연결/해제 버튼 -->
                        <button id="connect"
                                class="btn btn-success"
                                type="button"
                                title="WebSocket 연결을 시작합니다">
                            🔗 연결
                        </button>

                        <button id="disconnect"
                                class="btn btn-danger"
                                type="button"
                                disabled="disabled"
                                title="WebSocket 연결을 종료합니다"
                                style="margin-left: 5px;">
                            ❌ 끊기
                        </button>
                    </form>

                    <!-- 연결 상태 표시 -->
                    <div id="connection-status" class="connection-status">
                        🔴 연결 해제
                    </div>
                </div>
            </div>
        </div>

        <!-- 메시지 전송 섹션 -->
        <div class="col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">💬 메시지 전송</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group" style="width: 100%;">
                            <label for="content">메시지:</label>
                            <input type="text"
                                   id="content"
                                   class="form-control"
                                   placeholder="메시지를 입력하세요... (Enter키로 전송)"
                                   maxlength="500"
                                   style="width: 70%; margin-left: 5px; margin-right: 10px;"
                                   disabled>

                            <!-- 전송 버튼 -->
                            <button id="send"
                                    class="btn btn-primary"
                                    type="button"
                                    disabled="disabled"
                                    title="메시지를 모든 사용자에게 전송합니다">
                                📤 Send
                            </button>
                        </div>


                    </form>

                </div>
            </div>
        </div>
    </div>

    <!-- 채팅 메시지 표시 영역 -->
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        📋 채팅 메시지
                        <small class="text-muted">실시간으로 업데이트됩니다</small>
                    </h3>
                </div>
                <div class="panel-body" style="padding: 0;">
                    <!--
                        채팅 메시지가 표시되는 테이블
                        - JavaScript의 showMessage() 함수가 동적으로 메시지 추가
                        - 입장 알림, 채팅 메시지 등이 시간순으로 표시
                    -->
                    <div class="table-responsive chat-container">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>
                                    💬 채팅 메시지
                                    <small>(최대 100개까지 표시)</small>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="chat-messages">
                            <!--
                                동적으로 생성되는 메시지들이 여기에 추가됩니다.
                                예시 구조:
                                <tr class="chat-message">
                                    <td>
                                        <span class="message-time">[14:30:25]</span>
                                        홍길동: 안녕하세요!
                                    </td>
                                </tr>
                            -->
                            <tr class="info-message">
                                <td class="text-muted text-center">
                                    🎯 채팅을 시작하려면 이름을 입력하고 연결 버튼을 클릭하세요.
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 기술 정보 및 도움말 -->
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        🔧 기술 정보
                        <button class="btn btn-xs btn-default pull-right" onclick="toggleTechInfo()">
                            상세보기
                        </button>
                    </h3>
                </div>
                <div class="panel-body" id="tech-info" style="display: none;">
                    <div class="row">
                        <div class="col-md-6">
                            <h4>🌐 사용 기술</h4>
                            <ul>
                                <li><strong>백엔드:</strong> Spring WebSocket + STOMP</li>
                                <li><strong>프론트엔드:</strong> JavaScript + StompJS</li>
                                <li><strong>프로토콜:</strong> WebSocket over HTTP</li>
                                <li><strong>메시지 형식:</strong> JSON</li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <h4>🔄 메시지 흐름</h4>
                            <ol>
                                <li>클라이언트 → <code>/app/chat</code> (메시지 발행)</li>
                                <li>서버 → <code>@MessageMapping</code> (메시지 처리)</li>
                                <li>서버 → <code>/topic/chat</code> (브로드캐스트)</li>
                                <li>모든 구독자 → 메시지 수신</li>
                            </ol>
                        </div>
                    </div>

                    <div class="alert alert-info">
                        <strong>💡 개발자 도구 팁</strong>
                        <ul>
                            <li>네트워크 탭 : WebSocket 연결 상태를 확인 가능 (chat-app, 상태코드 101) </li>
                            <li>콘솔 탭 : 실시간 로그 확인</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- STOMP 클라이언트 JavaScript 로드 -->
<!--
    중요: 이 스크립트는 모든 HTML 요소가 로드된 후에 실행되어야 합니다.
    stomp.js 파일에는 STOMP 클라이언트 초기화 및 이벤트 처리 코드가 포함되어 있습니다.
-->
<script src="/resources/js/stomp.js"></script>

<script>
    /**
     * 기술 정보 패널 토글 함수
     * - 사용자가 기술 상세 정보를 보거나 숨길 수 있도록 함
     */
    function toggleTechInfo() {
        const techInfo = document.getElementById('tech-info');
        const button = event.target;

        if (techInfo.style.display === 'none') {
            techInfo.style.display = 'block';
            button.textContent = '숨기기';
        } else {
            techInfo.style.display = 'none';
            button.textContent = '상세보기';
        }
    }

    /**
     * 페이지 로드 시 초기화
     * - 메시지 입력 필드를 연결 상태에 따라 활성화/비활성화
     */
    document.addEventListener('DOMContentLoaded', function () {
        // 연결 상태에 따른 메시지 입력 필드 상태 관리
        const originalSetConnected = window.setConnected;
        window.setConnected = function (connected) {
            // 기존 setConnected 함수 실행
            if (originalSetConnected) {
                originalSetConnected(connected);
            }

            // 메시지 입력 필드 활성화/비활성화
            const contentInput = document.getElementById('content');
            contentInput.disabled = !connected;

            if (connected) {
                contentInput.placeholder = '메시지를 입력하세요... (Enter키로 전송)';
            } else {
                contentInput.placeholder = '연결 후 메시지를 입력할 수 있습니다';
            }
        };
    });
</script>

</body>
</html>