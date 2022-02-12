-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306:8889
-- Generation Time: Feb 12, 2022 at 12:15 PM
-- Server version: 10.6.4-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
                              `id` int(11) NOT NULL,
                              `slug` varchar(255) NOT NULL,
                              `title` varchar(255) NOT NULL,
                              `description` text DEFAULT NULL,
                              `rank` int(11) DEFAULT 0,
                              `deleted` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `slug`, `title`, `description`, `rank`, `deleted`) VALUES
                                                                                       (0, 'first-categorie-slug', 'First Categorie', 'Hello this is first categorie', 0, 0),
                                                                                       (1, 'second-categorie-slug', 'Second Categorie', 'Hello this is second categorie', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pages`
--

CREATE TABLE `pages` (
                         `id` int(11) NOT NULL,
                         `sub_id` int(11) DEFAULT 0,
                         `categorie_id` int(11) DEFAULT 0,
                         `slug` varchar(255) NOT NULL,
                         `title` varchar(255) NOT NULL,
                         `sub_title` varchar(255) DEFAULT NULL,
                         `short_data` text DEFAULT NULL,
                         `data` text DEFAULT NULL,
                         `seo_1` text DEFAULT NULL,
                         `seo_2` text DEFAULT NULL,
                         `rank` int(11) DEFAULT 0,
                         `created_time` datetime DEFAULT current_timestamp(),
                         `deleted` int(11) DEFAULT 0,
                         `img_url` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pages`
--

INSERT INTO `pages` (`id`, `sub_id`, `categorie_id`, `slug`, `title`, `sub_title`, `short_data`, `data`, `seo_1`, `seo_2`, `rank`, `created_time`, `deleted`, `img_url`) VALUES
                                                                                                                                                                             (1, 0, 0, 'hello-word-post', 'This is first blog post!', 'Hi i am first post', 'Hello okan Java Spring Boot :)', 'Hello Java Spring Boot :)', NULL, NULL, 0, '2022-01-30 01:39:13', 0, 'https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png'),
                                                                                                                                                                             (2, 0, 1, 'hello-word-post2', 'This is second blog post!', 'Hi subtitle for second post', 'Hello Java Spring Boot :) This is second blog post!', '<div class=\"entry-content\">\n					<p><iframe src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1202700664&amp;color=%23ff5500&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=true&amp;show_reposts=false&amp;show_teaser=false\" width=\"100%\" height=\"166\" frameborder=\"no\" scrolling=\"no\"><span data-mce-type=\"bookmark\" style=\"display: inline-block; width: 0px; overflow: hidden; line-height: 0;\" class=\"mce_SELRES_start\">&#xFEFF;</span></iframe></p>\n<div style=\"font-size: 10px; color: #cccccc; line-break: anywhere; word-break: normal; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif; font-weight: 100;\"><a style=\"color: #cccccc; text-decoration: none;\" title=\"Fikir Turu\" href=\"https://soundcloud.com/user-24274882\" rel=\"noopener\" onclick=\"javascript:window.open(\'https://soundcloud.com/user-24274882\'); return false;\">Fikir Turu</a> · <a style=\"color: #cccccc; text-decoration: none;\" title=\"Pişmanlık: Seçilmeyen yol\" href=\"https://soundcloud.com/user-24274882/pismanlik-secilmeyen-yol\" rel=\"noopener\" onclick=\"javascript:window.open(\'https://soundcloud.com/user-24274882/pismanlik-secilmeyen-yol\'); return false;\">Pişmanlık: Seçilmeyen yol</a></div>\n<p>Yaptığımız veya yapmadığımız bir şeyden ötürü hissettiğimiz o kederli duyguya pişmanlık diyoruz. Pişmanlığı kabul etmek, yanılabilir olduğumuzu anlamaktır: Ben de yanılabilirim, ayağım kayabilir, meğer dünyada her şey kontrolümde değilmiş. Güç yetiremediğim şeyler var. Kendime dair anlattığım hikâyede eksikler var, dünü kontrol edemediğim gibi, bugünkü hikâyemi de elden çıkarıyorum. Eski benliğimin yasını tutuyorum, hikâyemde boşluklar var ve orası pişmanlıklarla doldurulabilir ancak.</p>\n<p>Pişmanlık dikkatimizi geçmişten daha iyi yaşanabilecek bir geleceğe yöneltir. Daha evvel kaçırdığım bir dalgayı şimdi yakalamak, eşiğinde durup da açmadığım bir kapıyı şimdi aralamak, insan ilişkilerinde daha özenli olmak gibi. Bu anlamıyla pişmanlık, bizi kendimizin daha iyi bir haline ulaşmaya izin veren, buna dürten ve hatta icbar eden ahlaki bir duygu olarak da anlaşılabilir. Bu tarafıyla da bizi bir ahlak miyopluğundan çeker kurtarır, kendisine baktığında bir kusur göremeyen narsisizmimizi iyileştirir.</p>\n<p>Her birimizin bizi kovalayan bir geçmişi var ve o geçmişte iyi ya da kötü bir şeyler yapmış bulunuyoruz. Hatta geçmişin iyisi şimdinin kötüsüne dönüşebiliyor. ‘Geçmiş ölmemiştir, hatta geçmemiştir bile’ der Faulkner. Pişmanlık, sağlıklı biçimleriyle, yaşantılarımızdan öğrenmemize izin verir ve maziye ihtimam göstermemizi sağlar. Geçmişime ahlaki açıdan duyarlıyım ve yaşadıklarımın bana öğretmesine izin veriyorum! Gelecek, en güzel, geçmişten öğrenebilenlerin ruhuna ayak basar. Pişmanlık sağlıksız olduğunda ise bizi neyi kaybettiğimize dair bir düşünceye demirler: Orada ne geleceğin ufku vardır ne de geçmişin öğretmenliği. Sadece ebedi bir tasa halinde elimizden kayıp gitmiş olan şeylerin yasını tutarız.</p>\n<p><strong>Seçenek bolluğu</strong></p>\n<p>İnsan modern toplumda sürekli bir değişim haline muhatap oluyor: Kalıptan kalıba giren, ön görülemeyen, daima risk ve macera cevelanları üzerinde giden yeni bir hayat tasavvuru bu. Hani meşhur klişede söylendiği gibi: Değişmeyen tek şey değişimin ta kendisi! Sosyologların; risk toplumu, tüketim toplumu, akışkan toplum, fragmanlar halinde yaşam, turist bakışı olarak isimlendirdiği bir toplumsal hal. Kimi psikologların da doymuş, bukalemun, her kıvamı alabilen (protean) benlik gibi kavramlarla tanımlamaya çabaladığı şey, bu ele avuca sığmaz, bulunduğu yerin kıvamını kolayca alan o uçarı kişilik aslında. Postmodern dönemlerde değişimin bir istisna olmaktan çıkıp kaideleşmesinin altında yatan esas saik, metropollerin kesik ve kesintisiz ritmi olduğu kadar, medyanın (ve sosyal medyanın) desteğiyle önümüze açılan hızlı hayat ve çeşitlilik.</p>\n<p>Bir seçenek bolluğu çağında yaşıyoruz. Bir hayatın arkası sıra koşup duruyoruz ve yavaşlarsak çok fırsatları tepeceğimizi zannediyoruz. O kadar zengin bir deneyim zenginliği serili duruyor ki önümüzde, deneyimlediğimiz şeyler, biz onları diğer ihtimallerle mukayese ettiğimizde bize olması gerektiği kadar tatmin edici gelmiyor.</p>\n<p>Seçme bolluğu bir yüke dönüşüyor ve bu da tatminsizliğe yol açıyor. Maddi ve sosyal şartlarımız daha iyiye gittikçe karşılaştırma ölçütlerimiz de yükseliyor. Beklenti ve heveslerimiz, hazzın sıfır noktasıyla birlikte yükselmeye başlıyor. Beklentilerimiz gerçekleştiği sürece belki daha iyi yaşıyor ancak yaşantılarımızdan eskisi kadar haz almamaya başlıyoruz. Ne kadar sosyal mukayese içine girersek o ölçüde mutsuz hissediyoruz. Modern hayatla birlikte doyasıya tattığımız seçme hürriyeti ve ihtimal çoğalması, bizi gerçekçi olmayan beklentilere yönlendiriyor. Yine modern hayatın düsturlarından birisi olan ‘hayatını kontrol edebilirsin’ yanılsaması, bizi kaderin görünmez silleleri karşısında yenik hissettiriyor.</p>\n<p><strong>Risk, hata ve pişmanlığın olmadığı bir yaşam</strong></p>\n<p>Anne Dufourmantelle, riski hayatın içinde bilinmedik bir alan açan, tutumlarımızı, varoluş tarzımızı belirleyen bir dönüşüm anı, şimdide olma imkânı olarak tanımladığı <em>Riske Övgü</em> adlı eserinde, Gilles Deleuze’ün “Anlam veren tek şey tekrar değil, farktır, değişme, bozulma, “yanlış nota” olarak ifade edilen şeydir.” sözünü aktarıyordu.</p>\n<p>Riskin, hatanın, pişmanlığın olmadığı bir yaşam, zamanın yükünün boşaltılamadığı, kişinin kendi şahsi zamanını, yalnızca kendinin değil, dahil olduğu topluluklarının yaşantısını da tekrarlarken tükettiği bir lüzumsuz vetire -adeta bir asansör müziği- olma niteliğine bürünür. Tekrarlar ve dönüşler insan yaşamında da müzik parçalarında olduğu gibi gerekli ve anlam yaratan bir unsurdur, ancak farklılığın kakafonisini ahenge kavuşturabilmek için. Tek notanın sürgit tekrarlandığı ya da sürekli aynı parçanın döngüsündeki bir müzik hayal edin. “Bir devrimi devrim yapan her şeyi kaybetmeye razı olmaktır. Ama böyle anlar ender görülür, zira her şeyi muhafaza etmeye can atarız. Mevcut hal, kendimizin kendimiz tarafından böyle ağır ağır yutulmasından beslenir.” diyor Dufourmantelle.</p>\n<p>Yeknesaklık kayıtsızlıkla atbaşı gider, riske girip pişman olmaktansa nereden ve nasıl bize çarpacağı belli olmayan o künt dünyaya karşı kendimizi korumaya alır, seçmekten vaz geçeriz. Çünkü seçmek, seçilmekten daha ağır sorumluluk yükler. İnsanlar, değişen yaşam manzarasına göre yeniden nizamı sağlamak, ahengi kurmak yerine, inisiyatif almayan emektar memurlar gibi duyarsızlaşıp zamanlarını kazasız belasız tamamlamayı tercih ederler.</p>\n<p>Üstelik seçmek de her zaman sahih bir eylem olmayabilir. İnsanların ekserisinin (yoksulların ve değişimden ürkenlerin) seçmekten anladıkları, istemedikleri ihtimalden kaçınmaktan ibarettir. Umut tükenmişse, hayatın daha iyi bir teklifle çıkageleceği ihtimali ikircimlidir. Bu sebeple pek de gönüllü olmadıkları halihazırda cari olan seçeneğe yönelirler.</p>\n<p>Hannah Arendt, “ehven-i şer’in de aslında bir şer olduğunu” çoğunlukla unuturuz diyordu. Özgür irade, seçenekleri artırmakla ilgilidir. İnsan, attığı her adımda yüzlerce farklı yol üzerinde aynı anda yürür. Elbette geldiğimiz yolu iptal edemeyiz, atmış olduğumuz her adım, oynanmış satranç hamleleri gibi sonraki seçimlerimizin sayısını giderek eksiltir, hayata her an sıfırdan başlamamız mümkün değildir. Ancak insan, sorumluluk alan canlıdır da. İnsan, özgürlüğünü sorumluluk alabilmesi pahasına ele geçirmiştir. Bu yüzden, yaşamımızdaki tüm seçimlerimizden, sorumluluğunu karşılamak şartıyla pişman olmaya ve vaz geçmeye hakkımız vardır. İnsan, hayatın dönemeçli yollarında fikrini değiştirebilir.</p>\n<p><strong>Hata yapma korkusu</strong></p>\n<p>İsmet Özel, <em>Münacaat</em>’ındaki “Hata yapmak fırsatını Adem’e veren sendin/ bilmedim onun talihinden ne kadar düştü bana/gençtim ben ve neden hata payı yok diyordum hayatımda.” mısralarındaki gibi, gençler hata yapma hakları olmadığı zannıyla özgün bir hayatı yaşama hakkından da kendilerini mahrum ederler. Ve yaşamın sonunda artık böyle bir hayatı isteseler de kuramayacakları, hayatlarının da herkesinki kadar ve bir o kadar aynı, birörnek olduğunun hayal kırıklığıyla uzlaşmaya çalışırlar.</p>\n<p>Gençliklerinde olduğu gibi yaşlılıklarında da yanılıyorlar zannımca. Hiçbir yaşam için bitmeden, kişi son nefesini bırakmadan önce “mutluydu” ya da “mutsuzdu” diyemeyiz. Her seçim, hatta seçilmemiş her olgu bir yaşamı enkaza çevirebilir. Büyük hayat seçimlerinin en büyük riski alternatif ve öngörülemez bir gelecek yaratması değil, geçmişi de yeniden yorumlamaya tabi kılmasıdır.</p>\n<p>İnsan için asıl tehlike, her şeyden daha fazla, hata yapma korkusuyla bir seçim yapmaması, kötü bir resim asarım korkusuyla duvarına hiçbir resim asmamasıdır. Ama bu bile, hak kazanılması gereken bir tehlike aslında. İnsan ancak yanıtlayabileceği soruları işitir. Seçeneklerimizi görebilmek yaşamın o noktasına kadar irade ve özgürlük hakkında, benlik ideali ve kendiliğin imkanları hakkında ruhun çiftçiliğini ne kadar yapabildiğimize göre değişir. Kader, imkanları seçemeyeceklere sadece iki kart uzatır; bir şeyi yapmak, ya da onu yapmamak.</p>\n<p>Renata Salecl, <em>Seçme İkilemi</em> adlı kitabında, kapitalist düzenin, sunduğu içi boş seçeneklerle insanları nasıl hayatlarını istedikleri gibi şekillendirebilecekleri yanılsamasına sevk ettiğini, doğru duvar kâğıdını ya da saç kremini bulmakla doğru hayatı bulmanın eşdeğer tutulduğu, aynı ilkelere tabi olduğunu anlatıyor. Kişinin kendini geliştirmesi ve seçimlerini kılı kırk yararak yapması halinde başarının ışıltılı halesini taşıyabileceğinin, aksi takdirde başarısızlığın tamamen onun kusuru olduğu kabulünün, sosyal düzenin adaletsizliğini, haksızlığını, yanlışlığını sorgulamaktan insanları alıkoymasının bir göz bağcılığı olduğunu izah ediyor.</p>\n<p>Seçmediklerimizin pişmanlığı bizi bir ömür boyu avlamaya devam eder. René Girard da “İnsan, daima Öteki’nin arzusuna göre arzular.” diyordu. Henüz seçme aşamasına bile varmadan önce, yüreğimizde karşılığı olmayan şeylerin arzusu virüs gibi yerleşiyor. Herkesin arzuladıklarını seçmek, onlara ulaşacak vasıtaları, yöntemleri, insanları seçmek, gönle galebe çalan mantığın kararı. Kaybetsek de geride kalsak da pişmanlığını yaşamayacağımız, sorumluluğunu almayacağımız tek karar. Oysa, “Bir ormanda yol ikiye ayrıldı ve ben/ Ben gittim daha az geçilmişinden/ Ve bütün farkı yaratan bu oldu işte.” diyor Robert Frost.</p>\n<p><strong>Kaybetme korkusu</strong></p>\n<p>Bireye yönelik, tüketim ve şöhretin egemen olduğu bir toplumda, sürekli olarak kendimiz için daha iyi bir yaşamı seçmeye teşvik ediliyoruz. Her bir seçimin ağırlığı ve seçeneklerin aşırı bolluğu, bizi felce uğratan bir endişeye neden olabiliyor.</p>\n<p>İstediğimizi elde ettiğimizde, tatminin yerini hızla memnuniyetsizlik ve daha iyi bir seçenek arzusu alıyor. Kapitalist toplum, çarkları döndürmek ve aynı zamanda varlığını korumak için seçim ideolojisini kullanıyor. Hayatını seç ve seçimlerinle kendini yarat! Kendini inşa eden insan. Kendi özel arzularımıza sımsıkı sarılmışsak, daha adil ve eşitlikçi bir toplumun inşası için bir tuğla taşımaya zamanımız kalmayacaktır.</p>\n<p>Salecl, “İnsan tüketeceği ürünleri seçerken belirli sınırlar çerçevesinde özgür olsa da parçası olduğu sistemi seçme konusunda özgür olmak bir yana seçme şansı dahi olmayabiliyor. Seçim yapılacak olasılıkların çokluğu bir yana kaybetme korkusu da seçme eylemini zorlaştırıyor.” sözleriyle, insanın seçmekten kaçınmasının kararsızlık ve korkaklık olmasına rağmen, mazur görülebilecek bir durum olduğundan dem vuruyor. Bu, yetişkin insanın, çocukluktaki oyun oynama tarzına mesafe koymasıyla da alakalı bir hal. Johan Huizinga, <em>Homo Ludens</em> (oyun oynayan insan) diyordu, yeryüzünde insana ait her şeyin köken ilkesinin oyun oynamak olduğu, oyundaki gönüllü, özgür bir eyleme dayalı, maddi çıkar ve yarardan uzak, coşkulu ruh halinin insanın asli güdüsü olduğunu anlattığı eserinde. Çocuklar, oyunun kurallarını da kendileri belirler ve <em>-mış gibi</em> yaparlar. Ancak, aslında oyun oynamak bir çocukluk eylemi değil bir yetişkinleşme temrini, alıştırmasıdır. Çocuklar oyun oynayarak ideal insana ulaşmanın tarzını kazanırlar. Modern sonrası insanın unuttuğu, kökendeki bu ilkedir. Salecl, “Aralarında seçim yapılacak seçeneklerin sayısı bu kadar fazlayken, seçmek bu kadar bunaltıcı ve yanlış seçim yapmanın sorumluluğu bu kadar kaygı uyandırıcı görünürken, kararsızlığa saplanıp kalmak, seçimin doğurabileceği olası pişmanlık ve hayal kırıklığına karşı bir koruma sağlıyor sanki.” diye yazıyor.</p>\n<p>İnsanlar seçmiyor, seçilmişlere yöneliyor, inanmıyor, inanca inanıyor. Mükemmel ve tüm arzuları karşılayacak bir seçim olmadığına ve seçmek diğer seçenekleri reddetmek manasına geldiğine göre, pişman olacak bir şey, apaçık bir hata yapmamak en makul tavır gibi görünüyor ekseriyetle. “Rasyonel seçimde olsun bilinçdışı seçimde olsun, seçim yaptığımızda daima kaybettiğimiz bir şey vardır. Bağlanma korkusu teşhisi konulan kişiler çoğunlukla, seçim yaparak vazgeçmek zorunda kalacakları bir seçeneği bırakmaktan korkarlar.”</p>\n<p><strong>Seçeneklerin bolluğu ve mutsuzluk</strong></p>\n<p>Barry Schwartz <em>Bolluk Paradoksu</em> adlı kitabında, modernitenin başarısının hem acı hem de tatlı olduğunu söylüyor. “Atalarımızın en fazla hayalini kurabileceği şeyi başardık ama bunun büyük bir bedeli oldu. Ne istersek elde ediyoruz ama günün sonunda istediğimiz şeyin bizi umduğumuz kadar tatmin etmediğini görüyoruz. Etrafımız modern, zaman kazandıran cihazlarla çevrili ama asla yeteri kadar zamanımız olmuyor. Yaşamlarımızın yazarı olmakta özgürüz ama tam olarak nasıl yaşamlar “yazmak” istediğimizi bilmiyoruz.”</p>\n<p>Toplumbilimciler tam da hayatımızda çok fazla seçeceğimiz olduğu için güvensiz ve mutsuz hissettiğimizi iddia ediyor. Gerçekten de sınırları olmayan bir dünyada mı yaşıyoruz? Dostoyevski’nin “Tanrı yoksa her şey mübahtır.” sözüne karşılık, Lacan “Tanrı varsa her şey mübahtır.” demişti. Her iki iddiayı birleştirecek argüman olarak belki, ‘Tanrı’nın olmadığı bir dünyada insanın nelerin mübah olduğu konusundaki kaygısı tahammülfersa bir yüktür’ denebilir.</p>\n<p>Modern yaşamda çok fazla seçeneğe sahip olmak, bilhassa pişmanlık korkusu, statü endişesi, uyum çabası, sosyal kıyaslama ve her şeyin en iyisine sahip olma arzusuyla birleştiğinde psikolojik sorunlara kapı aralıyor. Sosyal kıyaslamada en iyinin define avcıları, başarısızlıklarından yalnızca kendilerini sorumlu tutarak depresyonun namzetleri haline geliyor. Dünya genelinde en yüksek intihar oranları, İskandinav ülkeleri ve Güney Kore, Japonya gibi, vatandaşlarının bireysel özgürlüğe ve kontrol, disiplin duygusuna değer verdiği toplumlarda görülüyor. Kierkegaard, insan bilgeliğinin özü olarak nitelendirdiği şu satırları yazmıştı <em>Ya/ Ya da</em> eserinde; “Evlenin ya da evlenmeyin… Ya da her ikisi içinde pişman olun. Dünyanın aptallığına kahkahayla gülün, pişman olun. Onun için ağlayın ve yine pişman olun…” Pişmanlığı kabul, yeryüzünde yanılabilir bir insan olduğumuzu da kabulleniştir. Geçmiş orada durmaya devam ediyor ama yarını, oradan öğrendiğimle, yepyeni bir biçimde inşa edebilirim.</p>\n<p><strong>Samimi pişmanlık</strong></p>\n<p>Samimi pişmanlık, dünden daha iyi yaşanacak bir geleceği bahşeder ancak yürümediğimiz yolların pişmanlığı hep daha fazladır. İnsan, başarılı olsa bile, yaşamındaki her seçiminin sonucuna çabucak uyum sağladığı için, aklı hep seçmediğinde kalıyor. “Her nerede değilsem orada mutlu olacakmışım gibi gelir bana” der Baudelaire.</p>\n<p>Mademki her halükârda seçtiğimiz yahut seçmediğimizden ötürü pişmanlık duyacağız, daha büyük ve daha iyi yenilelim. Büyük şairimiz Sezai Karakoç’un dediği gibi, ‘yenilgi yenilgi büyüyen bir zafer vardır’, yeter ki biz hatalarımızdan yüce ve güzel olana giden yolu yürümeyi bilelim. Pişmanlık seçilmeyen yoldadır, insanı inşa eden ise bütün bedelleri göze alarak ‘az seçilen yol’u yürümektir.</p>\n<p><strong>Bu makalede yer alan fikirler yazara aittir ve Fikir Turu’nun editöryel politikasını yansıtmayabilir.</strong></p>\n<p><strong>Bu yazı ilk kez 25 Ocak 2022’de yayımlanmıştır.</strong></p>\n<div class=\"essb_links essb_displayed_bottom essb_share essb_template_grey-fill-retina essb_1276713423 essb_size_xs essb_links_center print-no\" id=\"essb_displayed_bottom_1276713423\" data-essb-postid=\"15011\" data-essb-position=\"bottom\" data-essb-button-style=\"icon\" data-essb-template=\"grey-fill-retina\" data-essb-counter-pos=\"hidden\" data-essb-url=\"https://fikirturu.com/insan/pismanlik-secilmeyen-yol/\" data-essb-fullurl=\"https://fikirturu.com/insan/pismanlik-secilmeyen-yol/\" data-essb-instance=\"1276713423\"><ul class=\"essb_links_list essb_force_hide_name essb_force_hide\"><li class=\"essb_item essb_link_twitter nolightbox\"> <a href=\"#\" title=\"Share on Twitter\" onclick=\"essb.window(\'https://twitter.com/intent/tweet?text=Pişmanlık%3A%20Seçilmeyen%20yol&amp;url=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F&amp;counturl=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F\',\'twitter\',\'1276713423\'); return false;\" target=\"_blank\" rel=\"nofollow\" class=\"nolightbox\"><span class=\"essb_icon essb_icon_twitter\"></span><span class=\"essb_network_name essb_noname\"></span></a></li><li class=\"essb_item essb_link_facebook nolightbox\"> <a href=\"https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F&amp;t=Pi%C5%9Fmanl%C4%B1k%3A+Se%C3%A7ilmeyen+yol\" title=\"Share on Facebook\" onclick=\"essb.window(\'https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F&amp;t=Pi%C5%9Fmanl%C4%B1k%3A+Se%C3%A7ilmeyen+yol\',\'facebook\',\'1276713423\'); return false;\" target=\"_blank\" rel=\"nofollow\" class=\"nolightbox\"><span class=\"essb_icon essb_icon_facebook\"></span><span class=\"essb_network_name essb_noname\"></span></a></li><li class=\"essb_item essb_link_linkedin nolightbox\"> <a href=\"https://www.linkedin.com/shareArticle?mini=true&amp;ro=true&amp;trk=EasySocialShareButtons&amp;title=Pi%C5%9Fmanl%C4%B1k%3A+Se%C3%A7ilmeyen+yol&amp;url=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F\" title=\"Share on LinkedIn\" onclick=\"essb.window(\'https://www.linkedin.com/shareArticle?mini=true&amp;ro=true&amp;trk=EasySocialShareButtons&amp;title=Pi%C5%9Fmanl%C4%B1k%3A+Se%C3%A7ilmeyen+yol&amp;url=https%3A%2F%2Ffikirturu.com%2Finsan%2Fpismanlik-secilmeyen-yol%2F\',\'linkedin\',\'1276713423\'); return false;\" target=\"_blank\" rel=\"nofollow\" class=\"nolightbox\"><span class=\"essb_icon essb_icon_linkedin\"></span><span class=\"essb_network_name essb_noname\"></span></a></li><li class=\"essb_item essb_link_whatsapp nolightbox\"> <a href=\"whatsapp://send?text=Pişmanlık%3A%20Seçilmeyen%20yol%20https://fikirturu.com/insan/pismanlik-secilmeyen-yol/\" title=\"Share on WhatsApp\" onclick=\"essb.whatsapp(\'Pişmanlık%3A%20Seçilmeyen%20yol%20https://fikirturu.com/insan/pismanlik-secilmeyen-yol/\', \'1276713423\'); return false;\" target=\"_blank\" rel=\"nofollow\" class=\"nolightbox\"><span class=\"essb_icon essb_icon_whatsapp\"></span><span class=\"essb_network_name essb_noname\"></span></a></li><li class=\"essb_item essb_link_mail nolightbox\"> <a href=\"mailto:?subject=&amp;body=\" title=\"Share on Email\" onclick=\"essb.tracking_only(\'\', \'mail\', \'1276713423\', true);\" target=\"_self\" rel=\"nofollow\" class=\"nolightbox\"><span class=\"essb_icon essb_icon_mail\"></span><span class=\"essb_network_name essb_noname\"></span></a></li></ul></div><div class=\"essb_break_scroll\"></div><!--<div class=\"posttags\"><a href=\"https://fikirturu.com/tag/anne-dufourmantelle/\" rel=\"tag\">Anne Dufourmantelle</a><a href=\"https://fikirturu.com/tag/gilles-deleuze/\" rel=\"tag\">Gilles Deleuze</a><a href=\"https://fikirturu.com/tag/hannah-arendt/\" rel=\"tag\">Hannah Arendt</a><a href=\"https://fikirturu.com/tag/ismet-ozel/\" rel=\"tag\">İsmet Özel</a><a href=\"https://fikirturu.com/tag/kapitalizm/\" rel=\"tag\">kapitalizm</a><a href=\"https://fikirturu.com/tag/ozgurluk/\" rel=\"tag\">Özgürlük</a><a href=\"https://fikirturu.com/tag/pismanlik/\" rel=\"tag\">pişmanlık</a><a href=\"https://fikirturu.com/tag/psikoloji/\" rel=\"tag\">Psikoloji</a><a href=\"https://fikirturu.com/tag/renata-salecl/\" rel=\"tag\">Renata Salecl</a><a href=\"https://fikirturu.com/tag/rene-girard/\" rel=\"tag\">René Girard</a><a href=\"https://fikirturu.com/tag/risk/\" rel=\"tag\">risk</a><a href=\"https://fikirturu.com/tag/secimler/\" rel=\"tag\">seçimler</a><a href=\"https://fikirturu.com/tag/secme-ikilemi/\" rel=\"tag\">Seçme İkilemi</a><a href=\"https://fikirturu.com/tag/sezai-karakoc/\" rel=\"tag\">Sezai Karakoç</a><a href=\"https://fikirturu.com/tag/tuketim-toplumu/\" rel=\"tag\">tüketim toplumu</a></div>	-->\n					</div>', NULL, NULL, 0, '2022-01-31 01:37:13', 0, 'https://vid.alarabiya.net/images/2021/09/08/6b221f49-728a-4a2b-a1f6-7529e4bbe998/6b221f49-728a-4a2b-a1f6-7529e4bbe998_16x9_1200x676.jpg?width=1138');

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
                            `id` int(11) NOT NULL,
                            `key` varchar(255) NOT NULL,
                            `value` text NOT NULL,
                            `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `key`, `value`, `description`) VALUES
                                                                 (1, 'FOOTER_LICANCE', 'MIT', 'footer also appears at the bottom'),
                                                                 (2, 'FOOTER_DESC', 'Okan Bahadır Soygür', 'footer also appears at the bottom'),
                                                                 (3, 'LOGO_URL', 'https://spring.io/images/projects/spring-framework-640ad1b04f7efa89e0f0f7353e6b5e02.svg?v=2', 'url to go to when you press logo'),
                                                                 (4, 'LOGO_TEXT', 'Java Spring Boot Blog', 'text to be written next to the logo'),
                                                                 (5, 'GITHUB_LINK', 'https://github.com/okanbahadirsoygur/Java-Spring-Boot-and-MariaDB-Blog-Example', 'github link');

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

CREATE TABLE `slider` (
                          `id` int(11) NOT NULL,
                          `title` text DEFAULT NULL,
                          `img_url` text DEFAULT NULL,
                          `rank` int(11) DEFAULT 0,
                          `url` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`id`, `title`, `img_url`, `rank`, `url`) VALUES
                                                                   (1, 'Slider 1', 'https://www.rspca.org.uk/documents/1494939/0/what+to+do+with+stray+cats+and+kittens+%283%29.jpg/886bc0d5-1dc5-d2bf-eabd-473fd4d99886?t=1618404272031', 2, 'http://google.com'),
                                                                   (2, 'Slider 2', 'https://api.time.com/wp-content/uploads/2022/01/Biden-Cat-01.jpg?w=1600&quality=70', 1, 'http://facebook.com'),
                                                                   (7, 'Star Wars', 'https://www.nestle-cereals.com/tr/sites/g/files/fawtmp401/files/styles/stage_visual_large/public/d7/nq_starwars_slider_2048x1152_3.jpg?itok=yIDk6tTz', 4, 'http://instagram.com'),
                                                                   (11, 'yaz', 'https://www.lifewire.com/thmb/BQdMoVsHlk8HaZiZ2tv0elBfFO8=/1920x1080/filters:no_upscale():max_bytes(150000):strip_icc()/tropical-beach-wallpaper-beach-backgrounds-587fbb765f9b584db3241860.jpg', 1, 'dsfdfs'),
                                                                   (12, 'star', 'https://wallpaperaccess.com/full/1347951.jpg', 0, 'fdsdfsfds');

-- --------------------------------------------------------

--
-- Table structure for table `urunler`
--

CREATE TABLE `urunler` (
                           `id` int(11) NOT NULL,
                           `isim` varchar(100) DEFAULT NULL,
                           `fiyat` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `urunler`
--

INSERT INTO `urunler` (`id`, `isim`, `fiyat`) VALUES
                                                  (1, 'Macbook M1', 20000),
                                                  (2, 'deneme123', 43),
                                                  (3, 'deneme123', 43),
                                                  (4, 'deneme123', 43),
                                                  (5, 'deneme123', 43),
                                                  (6, 'deneme123', 43),
                                                  (7, 'deneme123', 43),
                                                  (8, 'deneme123', 43),
                                                  (9, 'deneme123', 43),
                                                  (10, 'deneme123', 43),
                                                  (11, 'deneme123', 43),
                                                  (12, 'deneme123', 43),
                                                  (13, 'deneme123', 43),
                                                  (14, 'deneme123', 43),
                                                  (15, 'deneme123', 43),
                                                  (16, 'deneme123', 43),
                                                  (17, 'deneme123', 43),
                                                  (18, 'deneme123', 43),
                                                  (19, 'deneme123', 43),
                                                  (20, 'deneme123', 43),
                                                  (21, 'deneme123', 43),
                                                  (22, 'deneme123', 43),
                                                  (23, 'deneme123', 43),
                                                  (24, 'deneme123', 43),
                                                  (25, 'deneme123', 43),
                                                  (26, 'deneme123', 43),
                                                  (27, 'deneme123', 43),
                                                  (28, 'deneme123', 43);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pages`
--
ALTER TABLE `pages`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `slider`
--
ALTER TABLE `slider`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `urunler`
--
ALTER TABLE `urunler`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pages`
--
ALTER TABLE `pages`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `slider`
--
ALTER TABLE `slider`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `urunler`
--
ALTER TABLE `urunler`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
