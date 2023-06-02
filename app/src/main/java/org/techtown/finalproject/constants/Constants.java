package org.techtown.finalproject.constants;

public class Constants {
    public enum EDatabase{
        userDatabase("userDatabase"),
        gangjwaDatabase("gangjwaDatabase");

        private String text;
        private EDatabase(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum ELoginDialog {
        idEditOption("defaultInputmode = english;"),
        idIsNull("아이디를 입력해주세요."),
        pwIsNull("비밀번호를 입력해주세요."),
        idNotFound("존재하지 않는 아이디입니다."),
        pwNotFound("비밀번호가 일치하지 않습니다."),
        loginSuccess("로그인 성공"),
        intentName("name"),
        intentStudentId("studentId"),
        intentId("id"),
        intentPw("pw"),
        onBackPressed("'뒤로' 버튼을 한번 더 눌러 종료합니다.");

        private String text;
        private ELoginDialog(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum EFindInfo {
        name("이름을 입력해주세요."),
        studentId("학번을 입력해주세요."),
        id("아이디: "),
        pw("   비밀번호: "),
        nullEdit(""),
        infoNotFound("입력하신 정보의 회원이 존재하지 않습니다.");

        private String text;
        private EFindInfo(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum ERegistration {
        idEditOption("defaultInputmode = english;"),
        nullEdit(""),
        name("이름을 입력해주세요."),
        studentId("학번을 입력해주세요."),
        id("아이디를 입력해주세요."),
        pw("비밀번호를 입력해주세요."),
        confirm("입력한 비밀번호와 일치하지 않습니다."),
        idDuplication("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요."),
        studentIdDuplication("이미 존재하는 학번(학생)입니다. 회원 정보 찾기를 이용해주세요."),
        registerSuccess("회원가입 성공"),
        needAgreement("개인정보 수집에 동의해주세요.");

        private String text;
        private ERegistration(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum EMainFrame{
        intentName("name"),
        intentStudentId("studentId"),
        intentId("id"),
        intentPw("pw"),
        welcomeText("님 환영합니다!"),
        logout("로그아웃 되었습니다."),
        contact("tel:010-2888-8614"),
        myiweb("https://myiweb.mju.ac.kr/servlet/main1"),
        space(" "),
        selectTitle("강좌 선택"),
        selectMessage("강좌: "),
        selectPositiveButton("신청"),
        sincheongType("sincheong"),
        sincheongSuccess("신청 완료"),
        sincheongDuplicated("이미 신청된 강좌입니다."),
        selectNegativeButton("미리담기"),
        miriType("miri"),
        miriSuccess("미리담기 완료"),
        miriDuplicated("이미 미리담기된 강좌입니다."),
        onBackPressed("'뒤로' 버튼을 한번 더 눌러 종료합니다.");

        private String text;
        private EMainFrame(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum EAccount{
        intentName("name"),
        intentStudentId("studentId"),
        intentId("id"),
        intentPw("pw"),
        changeSuccess("비밀번호가 변경되었습니다."),
        nullEdit(""),
        confirmFailed("비밀번호 확인이 일치하지 않습니다."),
        pwFailed("기존 비밀번호가 일치하지 않습니다."),
        deleteTitle("주의"),
        deletemessage("정말로 계정을 삭제하시겠습니까?"),
        deletePositiveButton("확인"),
        deleteSuccess("삭제되었습니다."),
        deleteNegativeButton("취소");

        private String text;
        private EAccount(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum EMiri{
        intentId("id"),
        miriType("miri"),
        sincheongType("sincheong"),
        space(" "),
        selectTitle("강좌 선택"),
        selectMessage("강좌: "),
        selectPositiveButton("신청"),
        sincheongSuccess("신청 완료"),
        sincheongDuplicated("이미 신청된 강좌입니다."),
        selectNegativeButton("삭제"),
        deleteSuccess("삭제 완료");

        private String text;
        private EMiri(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public enum ESincheong{
        intentId("id"),
        miriType("miri"),
        sincheongType("sincheong"),
        space(" "),
        selectTitle("강좌 선택"),
        selectMessage("강좌: "),
        selectPositiveButton("미리담기"),
        miriSuccess("미리담기 완료"),
        miriDuplicated("이미 미리담기된 강좌입니다."),
        selectNegativeButton("삭제"),
        deleteSuccess("삭제 완료");

        private String text;
        private ESincheong(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }
}
