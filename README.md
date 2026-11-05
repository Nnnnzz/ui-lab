# Automated UI Testing Lab — ITDS362

งานนี้เลือกใช้เครื่องมือทดสอบ UI อัตโนมัติ 2 เครื่องมือตามโจทย์ ได้แก่

1. **Part 2: Selenium WebDriver in Java (JUnit)** — โฟลเดอร์ `selenium-java/` เก็บโปรเจกต์ Maven, ชุดทดสอบ JUnit, Page Object และภาพหลักฐานการทดสอบ คำสั่งสำหรับรันทดสอบคือ `cd selenium-java && mvn test`
2. **Part 4: Cypress** — โฟลเดอร์ `cypress/` เก็บการตั้งค่า Cypress, E2E test สำหรับ QP-01, การรอ element และการทดสอบ Web Form รวมถึงภาพ วิดีโอ และหลักฐาน API คำสั่งสำหรับรันทดสอบคือ `cd cypress && npm run test:e2e`

## การเตรียมสภาพแวดล้อม

โปรเจกต์นี้ทดสอบบน macOS (Apple Silicon) โดยใช้ Google Chrome และ ChromeDriver เวอร์ชันที่ตรงกัน รวมถึง OpenJDK 21, Maven และ Node.js ก่อนรัน Selenium ให้กำหนด JDK และตำแหน่ง ChromeDriver ใน Terminal ดังนี้

```bash
export JAVA_HOME="/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:/opt/homebrew/bin:$PATH"
export CHROMEDRIVER="$HOME/drivers/chromedriver-mac-arm64/chromedriver"
```

ตรวจสอบเวอร์ชันด้วย `java -version` และ `chromedriver --version` โดย ChromeDriver ต้องมีเวอร์ชันที่รองรับ Chrome ที่ติดตั้งอยู่

## วิธีติดตั้งและรันทดสอบ

### Selenium WebDriver in Java (JUnit)

```bash
cd selenium-java
mvn test
```

### Cypress

```bash
cd cypress
npm install
npx cypress verify
npm run test:e2e
```

## ข้อควรระวังในการทดสอบ QP-01

การทดสอบสร้างชื่อพิซซ่าโดยต่อวันและเวลาปัจจุบันในรูปแบบ `Lab Pizza YYYYMMDD-HHMMSS` เพื่อป้องกันชื่อซ้ำบนเว็บไซต์ฝึกปฏิบัติที่ใช้ร่วมกัน ผลลัพธ์ของเครื่องมือและแคลอรี่อาจแตกต่างกันในแต่ละครั้ง จึงตรวจสอบตามเงื่อนไขว่าไม่ใช่ Knife และมีแคลอรี่ไม่เกิน 600 ต่อชิ้น ไม่ควรรันทดสอบวนซ้ำโดยไม่จำเป็น และห้ามกด **Clear Ratings** เพราะจะลบข้อมูลการให้คะแนนของผู้ใช้อื่นด้วย

## หลักฐานและผลการทดสอบ

รายละเอียดเวอร์ชันเครื่องมือ, locator, ผลการทดสอบ, คำตอบ Part B และตำแหน่งไฟล์หลักฐานอยู่ใน [RESULTS.md](RESULTS.md) โดยภาพจาก Selenium อยู่ใน `screenshots/` และหลักฐานของ Cypress อยู่ใน `cypress/evidence/`, `cypress/screenshots/` และ `cypress/videos/`
