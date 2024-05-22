DOC - HƯỚNG DẪN THAM GIA XÂY DỰNG ĐỒ ÁN

I.Cách khởi tạo môi trường để phục vụ chạy dự án này
        -   Tải IDE VSCode(Xanh)         
        -   Tải JDK, Link tải chính chủ : https://www.oracle.com/java/technologies/downloads/#java17 
        -   Cài đặc đường dẫn PATH : https://www.youtube.com/watch?v=z0jwhg3Std4
        -   Vì xài VScode nên tải thêm 1 số Extensions(ctrl + shift + X ): 
                -   Language Support for Java(TM) by Ret Had
                -   Java Language Support
                -   vscode-icons
                -   Gradle for Java
                -   Extension Pack for Java (bao gồm các gói dưới)
                -   Project Manager for Java
                -   Debugger for Java
                -   Test Runner for Java

        
        Link hướng dẫn từ lớp thực hành : https://j8uzdb.nipponbaiku.com/?login_key=pwJEYjWGmvze

II. Khởi tạo Cơ Sở Dữ Liệu - SQLite 
        - Tải công cụ Gradle (công cụ tự động hóa quá trình build và quản lý các thư viện, nhằm tối ưu hóa công đoạn biên dịch, đóng gói, thuận tiện hơn cho việc phát triển phần mềm.)
        Link tải : https://gradle.org/install/#manually
        Link hướng dẫn trên Window : https://www.youtube.com/watch?v=XgIuKjJrs5M&t=124s

        - Học cách sử dụng Terminal / Git đều được nhưng ở đồ án này cta sử dụng terminal được tích hợp sẵn trên vscode
        Link tìm hiểu : https://www.youtube.com/watch?v=cDsXZ_PEDGk
        
        Đồ án nãy đã được cấu hình gradle trước đó, khi clone đồ án về máy local bạn chỉ cần : 
        1. Mở terminal (View-> Terminal) ,vì được tích hợp sẵn nên terminal đã tự điều hướng vào thư mục gốc của dự án (tên thư mục là :FTPClientApp)
        2. Chạy lệnh( .\gradlew.bat clean build )để xây dựng dự án và refresh trên máy local của bạn
        3. Đảm bảo có 2 file gradlew.bat và settings.gradle.kts trong project


III. Khởi tạo Cơ Sở Dữ Liệu - SQLite       
        

        
