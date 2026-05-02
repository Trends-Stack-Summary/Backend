package com.project.batch.constants

enum class Source(
    val displayName: String,
    val url: String,
    val type: CollectionType,
    val region: Region,
    val referer: String? = null,
    val userAgent: String? = null,
) {

    KAKAO_TECH("카카오 테크", "https://tech.kakao.com/feed/", CollectionType.RSS, Region.DOMESTIC),
    WOOWAHAN("우아한형제들 기술 블로그", "https://techblog.woowahan.com/feed/", CollectionType.RSS, Region.DOMESTIC, referer = "https://techblog.woowahan.com/"),
    KAKAO_PAY("카카오페이 테크", "https://tech.kakaopay.com/rss.xml", CollectionType.RSS, Region.DOMESTIC),
    LINE_ENGINEERING("LINE Engineering", "https://engineering.linecorp.com/ko/feed", CollectionType.RSS, Region.DOMESTIC),
    DAANGN("당근", "https://medium.com/feed/daangn", CollectionType.RSS, Region.DOMESTIC),
    MUSINSA("무신사 기술 블로그", "https://medium.com/feed/musinsa-tech", CollectionType.RSS, Region.DOMESTIC),
    NHN("NHN Cloud", "https://meetup.nhncloud.com/rss", CollectionType.RSS, Region.DOMESTIC),
    SOCAR("쏘카 기술 블로그", "https://tech.socarcorp.kr/feed.xml", CollectionType.RSS, Region.DOMESTIC),
    WATCHA("왓챠 기술 블로그", "https://medium.com/feed/watcha", CollectionType.RSS, Region.DOMESTIC),
    DEVSISTERS("데브시스터즈 기술 블로그", "https://tech.devsisters.com/rss.xml", CollectionType.RSS, Region.DOMESTIC),
    BANKSALAD("뱅크샐러드 기술 블로그", "https://blog.banksalad.com/rss.xml", CollectionType.RSS, Region.DOMESTIC),
    MARKET_KURLY("마켓컬리", "https://helloworld.kurly.com/feed/", CollectionType.RSS, Region.DOMESTIC),

    NAVER_D2("Naver D2", "https://d2.naver.com/home", CollectionType.HTML, Region.DOMESTIC),

    TOSS_TECH("토스 테크", "https://toss.tech", CollectionType.PLAYWRIGHT, Region.DOMESTIC),
    KAKAO_BANK("카카오뱅크 기술 블로그", "https://tech.kakaobank.com", CollectionType.PLAYWRIGHT, Region.DOMESTIC),
    OLIVE_YOUNG("올리브영 기술 블로그", "https://oliveyoung.tech/blog", CollectionType.PLAYWRIGHT, Region.DOMESTIC),

    NETFLIX("Netflix Tech Blog", "https://medium.com/feed/netflix-techblog", CollectionType.RSS, Region.INTERNATIONAL),
    AIRBNB("Airbnb Engineering", "https://medium.com/feed/airbnb-engineering", CollectionType.RSS, Region.INTERNATIONAL),
    GOOGLE("Google Developers Blog", "https://developers.googleblog.com/feeds/posts/default", CollectionType.RSS, Region.INTERNATIONAL),
    SLACK("Slack Engineering", "https://slack.engineering/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    AMAZON("Amazon AWS Blog", "https://aws.amazon.com/blogs/aws/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    GITHUB("Github Blog", "https://github.blog/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    SPOTIFY("Spotify Engineering", "https://engineering.atspotify.com/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    DROPBOX("Dropbox Tech Blog", "https://dropbox.tech/feed", CollectionType.RSS, Region.INTERNATIONAL),
    FACEBOOK("Facebook Engineering", "https://engineering.fb.com/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    CLOUDFLARE("Cloudflare Blog", "https://blog.cloudflare.com/rss/", CollectionType.RSS, Region.INTERNATIONAL),
    PINTEREST("Pinterest Engineering", "https://medium.com/feed/pinterest-engineering", CollectionType.RSS, Region.INTERNATIONAL),
    LYFT("Lyft Engineering", "https://eng.lyft.com/feed", CollectionType.RSS, Region.INTERNATIONAL),
    MICROSOFT("Microsoft Engineering", "https://devblogs.microsoft.com/engineering-at-microsoft/feed/", CollectionType.RSS, Region.INTERNATIONAL),
    STRIPE("Stripe Engineering", "https://stripe.com/blog/feed.rss", CollectionType.RSS, Region.INTERNATIONAL),
}