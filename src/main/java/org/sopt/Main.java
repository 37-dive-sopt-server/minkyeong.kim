package org.sopt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.sopt.controller.MemberController;
import org.sopt.domain.Member;
import org.sopt.enums.Gender;
import org.sopt.exception.ConsoleExceptionHandler;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;

public class Main {
    public static void main(String[] args) {

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberService(memberRepository);
        MemberController memberController = new MemberController(memberService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 회원 삭제 🗑️");
            System.out.println("5️⃣. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("이름: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("이메일: ");
                    String email = scanner.nextLine().trim();

                    System.out.print("생년월일(yyyy-MM-dd): ");
                    String birthRaw = scanner.nextLine().trim();

                    System.out.print("성별(male/female): ");
                    String genderRaw = scanner.nextLine().trim().toUpperCase();

                    try {
                        Long createdId = memberController.createMember(name, LocalDate.parse(birthRaw), email, Gender.valueOf(genderRaw));
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } catch (Exception e) {
                        System.out.println("❌ 회원 등록 중 오류 발생: " + ConsoleExceptionHandler.handle(e));
                    }
                    break;

                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);

                        if (foundMember.isPresent()) {
                            System.out.println("✅ 조회된 회원: ID=" + foundMember.get().getId() + ", 이름=" + foundMember.get().getName());
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;

                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;

                case "4": {
                    System.out.print("삭제할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        memberController.deleteMember(id);
                        System.out.println("🗑️ 삭제 완료 (ID: " + id + ")");
                    } catch (Exception e) {
                        System.out.println("❌ " + ConsoleExceptionHandler.handle(e));
                    }
                    break;
                }

                case "5":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;

                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}