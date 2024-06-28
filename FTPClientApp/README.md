-------------------------------------------------------------DOC - HƯỚNG DẪN THAM GIA XÂY DỰNG ĐỒ ÁN----------------------------------------------------------------

Project Plan Dự Án : https://sthuflitedu-my.sharepoint.com/:x:/r/personal/21dh114245_st_huflit_edu_vn/_layouts/15/Doc.aspx?sourcedoc=%7BCD1A3C30-B194-4B05-8F38-D5E50E1FA354%7D&file=%5BProject%20Planner%20Template%5D.xlsx&action=default&mobileredirect=true

rundll32.exe sysdm.cpl,EditEnvironmentVariables

I.Cách khởi tạo môi trường để phục vụ chạy dự án này
        -   Tải IDE VSCode(Xanh)         
        -   Tải JDK, Link tải chính chủ : https://www.oracle.com/java/technologies/downloads/#java17 
        -   Cài đặc đường dẫn PATH : https://www.youtube.com/watch?v=z0jwhg3Std4
        -   Vì xài VScode nên tải thêm 1 số Extensions(ctrl + shift + X ):              
                -   Java Language Support
                -   vscode-icons
                -   Gradle for Java
                -   Extension Pack for Java
                

        
        Link hướng dẫn từ lớp thực hành : https://j8uzdb.nipponbaiku.com/?login_key=pwJEYjWGmvze

II. Xem Cơ Sở Dữ Liệu - SQLite 

        - Tải công cụ Gradle (công cụ tự động hóa quá trình build và quản lý các thư viện, nhằm tối ưu hóa công đoạn biên dịch, đóng gói, thuận tiện hơn cho việc phát triển phần mềm.)
        Link tải : https://gradle.org/install/#manually
        Link hướng dẫn trên Window : https://www.youtube.com/watch?v=XgIuKjJrs5M&t=124s

        - Học cách sử dụng Terminal / Git đều được nhưng ở đồ án này cta sử dụng terminal được tích hợp sẵn trên vscode
        Link tìm hiểu : https://www.youtube.com/watch?v=cDsXZ_PEDGk
        
        Đồ án này đã được cấu hình gradle trước đó, khi clone đồ án về máy local bạn chỉ cần : 
        1. Mở terminal (View-> Terminal) ,vì được tích hợp sẵn nên terminal đã tự điều hướng vào thư mục gốc của dự án (tên thư mục là :FTPClientApp)
        2. Đảm bảo có 2 file gradlew.bat và settings.gradle.kts trong project 
        3. Chạy lệnh( .\gradlew.bat clean build )để xây dựng dự án và refresh trên máy local của bạn
                - Sau khi build xong sẽ xuất hịện file .gradle và build

        4. Tải SQLite .Link hướng dẫn nè : https://www.youtube.com/watch?v=-bDwNR_C0dE

        5. Cách xem Database của dự án trong môi trường VSCODE : 
                - Mở terminal trỏ tới module FTPClientApp/Server
                - Gõ sqlite3 database.db để sqlite compile file database.db
                - Gõ .tables hoặc .schema để xem CSDL của hệ thống 





      
        

        
