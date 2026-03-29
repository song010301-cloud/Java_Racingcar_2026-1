package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args)
    {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String nameinput = Console.readLine();
        String[] names = nameinput.split(",");

        // 1. 이름 관련 예외 처리 (빈값, 공백 포함, 5자 초과)
        for (int i = 0 ; i < names.length ; i++)
        {
            if (names[i].length() > 5) // 5자 초과
            {
                throw new IllegalArgumentException();
            }
            if (names[i].isEmpty()) // 빈값 (예: pobi,,woni)
            {
                throw new IllegalArgumentException();
            }
            if (names[i].contains(" ")) // 이름에 공백 포함 (예: pobi, woni)
            {
                throw new IllegalArgumentException();
            }
        }

        // 2. 이름 중복 예외 처리 (이중 for문으로 하나씩 비교)
        for (int i = 0; i < names.length; i++)
        {
            for (int j = i + 1; j < names.length; j++)
            {
                if (names[i].equals(names[j]))
                {
                    throw new IllegalArgumentException();
                }
            }
        }

        System.out.println("시도할 회수는 몇회인가요?"); // 요구사항에 맞춰 '회수는'으로 수정
        String countinput = Console.readLine();

        // 3. 횟수 관련 예외 처리 (숫자가 아님, 0 이하)
        int count;
        try
        {
            count = Integer.parseInt(countinput);
            if (count <= 0)
            {
                throw new IllegalArgumentException();
            }
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException();
        }

        int[] position = new int [names.length];

        System.out.println("\n실행 결과"); // '실행 결과' 띄어쓰기 수정 (테스트 통과용)
        for(int i = 0; i < count; i++)
        {
            for (int j = 0 ; j < names.length; j++)
            {
                int randomnum = camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(0,9);
                if (randomnum >= 4)
                {
                    position[j]++;
                }

                System.out.print(names[j] + " : "); // 콜론 앞뒤 공백 수정
                for(int k = 0; k < position[j]; k++)
                {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }

        int distance = 0;
        for(int i = 0 ; i < position.length; i++)
        {
            if(position[i] > distance)
            {
                distance = position[i];
            }
        }

        java.util.List<String> winner = new java.util.ArrayList<>();

        for (int i = 0 ; i < names.length; i++)
        {
            if (position[i] == distance)
            {
                winner.add(names[i]);
            }
        }

        System.out.println("최종 우승자 : " + String.join(", ", winner)); // 결과 출력
    }
}