# UI lab results

Tool parts that I chose: **Part 2: Selenium WebDriver in Java (JUnit)** และ **Part 4: Cypress**

## Part 0

- Step 0.1, Chrome version: Google Chrome 156.0.8078.80 (Official Build) (arm64)
- Step 0.3, ChromeDriver version: ChromeDriver 156.0.8078.80 (ตรงกับ Chrome)
- Step 0.4, locator of Pizza, Please!: `[name='pizza-please']`
- Step 0.4, locator of Excluded tools: `select`
- Step 0.4, locator of Must be vegetarian: `#default-checkbox`
- Step 0.4, locator of Custom Pizza Name: `input#pizza-name`
- Step 0.5, pizza name, tool and calories of the run by hand: `Lab Pizza 20261105-134213`, เครื่องมือ `Pizza cutter`, 400 calories per slice

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | เปิด QuickPizza และเข้าสู่ Advanced เพื่อกำหนดเงื่อนไขการสร้างพิซซ่า | Pass |
| 2 | กำหนด Max Calories per Slice เป็น 600 และเลือก Knife เป็นเครื่องมือที่ไม่ต้องการ | Pass |
| 3 | เลือก Must be vegetarian และกำหนดชื่อพิซซ่าเป็น `Lab Pizza 20261105-134213` | Pass |
| 4 | กด Pizza, Please! ได้ผลลัพธ์ชื่อพิซซ่าตรงกัน ใช้ Pizza cutter และมี 400 calories ต่อชิ้น | Pass |
| 5 | กด Love it! ขณะยังไม่ได้เข้าสู่ระบบ และเว็บไซต์แสดง `Please log in first.` | Pass |
| 6 | ขั้นตอนนี้ไม่ได้ยืนยันด้วยการทำมือในรอบ Manual walkthrough; ยืนยันการเข้าสู่ระบบ Admin ด้วยการทดสอบอัตโนมัติทั้ง Selenium และ Cypress | Automated test: Pass |
| 7 | ขั้นตอนนี้ไม่ได้ยืนยันด้วยการทำมือในรอบ Manual walkthrough; ยืนยันว่ารายการ Admin แสดงพิซซ่าที่สร้างและเครื่องมือสอดคล้องกับผลแนะนำด้วยการทดสอบอัตโนมัติ | Automated test: Pass |

## Part 2: Selenium WebDriver in Java

- Step 2.4, the failure line for the wrong title: `FirstTest.java:21` — JUnit แจ้ง `expected: <Web Form> but was: <Web form>` จากนั้นแก้ expected title ให้ตรงกับข้อความจริง
- Step 2.15, QP01Test run 1, result and time: ผ่าน (0 failures, 0 errors), ใช้เวลา 9.561 วินาที; ชื่อคลาสที่ใช้จริงในโปรเจกต์นี้คือ `QP01PageObjectTest` และชื่อพิซซ่า `Lab Pizza 20261105-140855`
- Step 2.15, QP01Test run 2, result and time: ผ่าน (0 failures, 0 errors), ใช้เวลา 22.48 วินาที; ชื่อพิซซ่า `Lab Pizza 20261105-141016`
- Step 2.20, full run 1, time and number of tests: ผ่าน 4 tests, 0 failures, 0 errors, ใช้เวลา 14.498 วินาที (`BUILD SUCCESS`)
- Step 2.20, full run 2, time and number of tests: ผ่าน 4 tests, 0 failures, 0 errors, ใช้เวลา 28.461 วินาที (`BUILD SUCCESS`)

หลักฐานเพิ่มเติม: ผลทดสอบที่ตั้งใจให้ผิดอยู่ใน `reports/selenium-step2-4-intentional-failure.txt`; log การทดสอบเต็มอยู่ใน `reports/selenium-java/final-run-2.txt` และ `reports/selenium-java/final-run-3.txt`; ภาพหน้าจออยู่ใน `screenshots/`

## Part 4: Cypress

- node --version, npm --version and npx cypress version: Node.js `v26.10.0`, npm `11.19.1`, Cypress `16.2.1`
- Step 4.3, failure message for the wrong title: Cypress แจ้ง `Timed out retrying after 10000ms` และแสดงค่าจริง `Web form` ไม่ตรงกับค่าที่คาดหวัง `Web Form` ที่ `e2e/web-form.cy.js:4:15`; จากนั้นแก้ expected title ให้ตรงกับค่าจริง
- Step 4.6, tool and calories in the answer of POST /api/pizza: `Pizza cutter`, 450 calories; หลักฐานอยู่ใน `cypress/evidence/qp01-api-response.json`
- Step 4.7, the line of the admin list that shows your pizza: `Lab Pizza 20261105-141414` พร้อมเครื่องมือ `Pizza cutter`; ตรวจสอบรายการ Admin สำเร็จ (`adminListVerified: true`) และมีภาพ `cypress/screenshots/qp01.cy.js/qp01-admin-success.png`
- Step 4.8, run 1, result, duration and pizza name: ผ่าน 3 specs / 3 tests, 0 failing, ใช้เวลา 13 วินาที; ชื่อพิซซ่า `Lab Pizza 20261105-140821`
- Step 4.8, run 2, result, duration and pizza name: ผ่าน 3 specs / 3 tests, 0 failing, ใช้เวลา 23 วินาที; ชื่อพิซซ่า `Lab Pizza 20261105-141046`
- Step 4.9 (optional), browser and result: Chrome 156 แบบ headless; ผ่านทั้ง 3 specs ในการตรวจสอบรอบสุดท้าย

หลักฐานเพิ่มเติม: log การทดสอบอยู่ใน `reports/cypress-final-run-2.txt`, `reports/cypress-final-run-4.txt` และ `reports/cypress-final-verification.txt`; การทดสอบที่ตั้งใจให้ผิดอยู่ใน `reports/cypress-step4-3-intentional-failure.txt`; วิดีโออยู่ใน `cypress/videos/`

## Part B

1. **เหตุใดจึงค้นหา Max Calories per Slice ผ่าน label แทนการใช้ id?**
   จากการตรวจ DOM พบว่า id ของช่องกรอกมีค่าซ้ำกัน ทำให้การใช้ id อาจเลือก element ผิดหรือไม่สามารถระบุช่องที่ต้องการได้อย่างแน่นอน การใช้ XPath ที่อ้างอิงข้อความ label แล้วเดินไปยัง input ที่สัมพันธ์กันจึงระบุช่อง Max Calories per Slice ได้ตรงกว่า หากแก้หน้าเว็บได้ ควรกำหนด id ที่ไม่ซ้ำและคงที่ เช่น `max-calories` ให้กับ input โดยตรง วิธีนี้ทำให้ locator สั้น อ่านง่าย และลดการพึ่งพาโครงสร้าง HTML รอบข้าง

2. **การเปรียบเทียบ tool ใน Admin list กับ tool ในผลแนะนำเป็น test oracle ประเภทใด และตรวจพบข้อผิดพลาดอะไรได้?**
   การตรวจนี้เป็น *consistency oracle* หรือการตรวจความสอดคล้องของผลลัพธ์ระหว่างส่วนต่าง ๆ ของระบบ เพราะนำค่า tool จากหน้าผลแนะนำมาเปรียบเทียบกับค่าที่แสดงในรายการ Admin หากระบบบันทึกหรือแสดงข้อมูลผิด เช่น ผลแนะนำระบุ Pizza cutter แต่หน้า Admin แสดง Knife การทดสอบจะตรวจพบความไม่ตรงกันได้ ในการทดสอบรอบนี้ Selenium และ Cypress ตรวจสอบชื่อพิซซ่าและ tool ในรายการ Admin ให้ตรงกับค่าที่ได้จากการสร้างพิซซ่า

3. **QP-01 ไม่ได้ตรวจว่าพิซซ่าเป็นมังสวิรัติ จะเพิ่มการตรวจสอบอย่างไร และต้องใช้ข้อมูลอะไร?**
   สามารถเพิ่ม assertion หลังสร้างพิซซ่าเพื่อตรวจว่า response จาก `POST /api/pizza` มีค่า `vegetarian: true` หรือส่วนประกอบทุกชนิดมีค่า `vegetarian: true` ในหลักฐาน API ของ Cypress รอบสุดท้าย response มีค่า `vegetarian: true` และส่วนประกอบแต่ละรายการระบุว่าเป็นมังสวิรัติ การตรวจนี้ต้องให้หน้าเว็บหรือ API ส่งสถานะ vegetarian กลับมาอย่างชัดเจน หรือมีแหล่งข้อมูลส่วนประกอบและคุณสมบัติอาหารที่เชื่อถือได้ให้ test ใช้เปรียบเทียบ

4. **เปรียบเทียบเครื่องมือทั้งสองด้านการรอ การเขียน QP-01 และรายงานผล**
   Selenium Java ใช้ explicit wait ผ่าน `WebDriverWait` และ `ExpectedConditions` ผู้เขียนต้องกำหนดเงื่อนไขรอในแต่ละจุดที่หน้าเว็บโหลดข้อมูล ส่วน Cypress มี automatic retry สำหรับคำสั่งและ assertion จึงเขียนการรอได้กระชับกว่า จากการทำ Lab นี้ Cypress เขียน flow และ assertion ในไฟล์เดียวได้เร็วกว่า ขณะที่ Selenium มีโครงสร้าง Page Object ที่แยก locator และ action ออกจาก test ทำให้จัดการโค้ดได้เป็นระบบ รายงาน Cypress อ่านผล pass/fail พร้อม screenshot และ video ได้สะดวก ส่วน Selenium แสดงชื่อ test, source line และผล Surefire ใน log ชัดเจน

5. **จะเลือกเครื่องมือใดสำหรับ Automated UI Testing ของ Project 2 และเพราะเหตุใด?**
   เลือก Selenium WebDriver เพราะแนวคิด WebDriver และการจัดการ locator สามารถนำไปต่อยอดกับงาน UI testing ที่เคยทำใน Group 09 ซึ่งใช้ Selenium WebDriver กับ pytest ได้ อีกเหตุผลคือการใช้ Page Object ช่วยรวม locator และ browser action ไว้ในจุดเดียว ทำให้การแก้ไขเมื่อหน้าเว็บเปลี่ยนทำได้ง่ายขึ้น ผลทดสอบใน Lab นี้ยังแสดงว่า Selenium สามารถรันครบ 4 tests ได้ 2 รอบโดยไม่มี failure หรือ error

6. **Part 2: หาก QuickPizza เปลี่ยน locator ของช่อง calories ต้องแก้ QP01Test และ QP01PageObjectTest อย่างไร และ Page Object ช่วยลดงานอย่างไร?**
   หาก test ใช้ locator โดยตรงใน `QP01Test` จะต้องแก้ locator ในไฟล์ test ที่อ้างถึงช่อง calories ทุกตำแหน่ง ส่วน `QP01PageObjectTest` ควรเรียก method ของ Page Object ต่อไปโดยไม่ต้องรู้รายละเอียด locator และให้แก้ locator ในคลาส `QP01Page` ที่รวม locator ของ QuickPizza ไว้ ในโปรเจกต์นี้ใช้ `QP01PageObjectTest` ร่วมกับ `QP01Page.java` จึงสามารถเปลี่ยน locator ที่ตัวแปร `calories` ใน Page Object ได้เพียงจุดเดียว การแยกแบบนี้ลดการแก้ซ้ำและทำให้ test method อ่านเป็นขั้นตอนของสถานการณ์ทดสอบมากกว่ารายละเอียดการค้นหา element

7. **Part 4: เหตุใด QP-01 จึงควรเป็น Cypress test เดียว และจะเกิดอะไรขึ้นหากแยกขั้นตอน 6 และ 7 เป็นอีก test?**
   QP-01 เป็น flow ต่อเนื่องที่สร้างพิซซ่าจากฝั่งผู้ใช้แล้วตรวจรายการเดียวกันในหน้า Admin จึงควรอยู่ใน test เดียวเพื่อใช้ชื่อพิซซ่าและข้อมูลผลลัพธ์ชุดเดิมร่วมกัน Cypress แยกสถานะระหว่าง test และล้าง cookies ก่อนเริ่ม test ถัดไป ทำให้การแยกขั้นตอน 6 และ 7 อาจทำให้ session หรือสถานะการเข้าสู่ระบบไม่ต่อเนื่อง อีกทั้ง test ที่สองจะต้องสร้างหรือค้นหาข้อมูลที่ test แรกสร้างไว้ ซึ่งเพิ่มความเสี่ยงจากข้อมูลที่ใช้ร่วมกันบน QuickPizza การรวม flow ไว้ใน test เดียวจึงช่วยให้ตรวจสอบความสัมพันธ์ระหว่างผลแนะนำและรายการ Admin ได้ตรงตามสถานการณ์

## Submission checklist

- [x] Part 0 environment and locator preparation
- [x] เลือกและทำ Part 2: Selenium WebDriver in Java
- [x] เลือกและทำ Part 4: Cypress
- [x] มีหลักฐาน intentional failure ของทั้งสองเครื่องมือ
- [x] ทดสอบ Selenium full suite สำเร็จ 2 รอบ
- [x] ทดสอบ Cypress full suite สำเร็จ 2 รอบ และมี final verification เพิ่มอีก 1 รอบ
- [x] README.md, RESULTS.md และ .gitignore
- [x] เก็บ screenshots, reports, API evidence และ Cypress videos
- [ ] Push ขึ้น GitHub repository สาธารณะชื่อ `ui-lab` และส่ง URL ใน MyCourses (ยังไม่ได้เชื่อม remote repository)
