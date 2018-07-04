package com.mozilla.telemetry.streaming

object StackTraceUtils {
  val sampleStackTrace: String =
    """
      |  "stackTraces": {
      |    "crash_info": {
      |      "address": "0x7fefdcc58ca",
      |      "crashing_thread": 0,
      |      "type": "EXCEPTION_BREAKPOINT"
      |    },
      |    "main_module": 6,
      |    "modules": [{
      |      "base_addr": "0x6c130000",
      |      "code_id": "5AF02C8Cb3000",
      |      "debug_file": "avcuf64.pdb",
      |      "debug_id": "70A75CAB333C44419141AD2BE5E217F31",
      |      "end_addr": "0x6c1e3000",
      |      "filename": "avcuf64.dll",
      |      "version": "3.13.18462.6565"
      |    }, {
      |      "base_addr": "0x74730000",
      |      "code_id": "56672A676000",
      |      "debug_file": "ksuser.pdb",
      |      "debug_id": "9DFBB91D94454F41BE7251E2156515562",
      |      "end_addr": "0x74736000",
      |      "filename": "ksuser.dll",
      |      "version": "6.1.7601.19091"
      |    }, {
      |      "base_addr": "0x77920000",
      |      "code_id": "5B0CBC6411f000",
      |      "debug_file": "kernel32.pdb",
      |      "debug_id": "CC40188259BF403B915ABB6F5B331BCE2",
      |      "end_addr": "0x77a3f000",
      |      "filename": "kernel32.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x77a40000",
      |      "code_id": "5824A140fa000",
      |      "debug_file": "user32.pdb",
      |      "debug_id": "9A43D3FD584F4A9698B7AB8D28BC8C502",
      |      "end_addr": "0x77b3a000",
      |      "filename": "user32.dll",
      |      "version": "6.1.7601.23594"
      |    }, {
      |      "base_addr": "0x77b40000",
      |      "code_id": "5B0CBC1E19f000",
      |      "debug_file": "ntdll.pdb",
      |      "debug_id": "F47155555C654CCCAECFD23B66F59D7D1",
      |      "end_addr": "0x77cdf000",
      |      "filename": "ntdll.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x77cf0000",
      |      "code_id": "4A5BC29D7000",
      |      "debug_file": "psapi.pdb",
      |      "debug_id": "0CABCB9659614C45A750FC85662A63D62",
      |      "end_addr": "0x77cf7000",
      |      "filename": "psapi.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x13f4a0000",
      |      "code_id": "5B2BB2A974000",
      |      "debug_file": "firefox.pdb",
      |      "debug_id": "140129529498447DBA25A77450E2E4BD2",
      |      "end_addr": "0x13f514000",
      |      "filename": "firefox.exe",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fedb200000",
      |      "code_id": "56672A4E143000",
      |      "debug_file": "msmpeg2adec.warbird.pdb",
      |      "debug_id": "976844A57D0948AABF95144FF177B5D62",
      |      "end_addr": "0x7fedb343000",
      |      "filename": "msmpeg2adec.dll",
      |      "version": "6.1.7601.23285"
      |    }, {
      |      "base_addr": "0x7fedb350000",
      |      "code_id": "53A49DCE2a9000",
      |      "debug_file": "msmpeg2vdec.pdb",
      |      "debug_id": "F44C78EF18E24DAE83FF0E187923F22C2",
      |      "end_addr": "0x7fedb5f9000",
      |      "filename": "msmpeg2vdec.dll",
      |      "version": "12.0.9200.17037"
      |    }, {
      |      "base_addr": "0x7fedb600000",
      |      "code_id": "5B2BB2FB189000",
      |      "debug_file": "mozavcodec.pdb",
      |      "debug_id": "5B563773D0804B70B16E93ABCD18975D2",
      |      "end_addr": "0x7fedb789000",
      |      "filename": "mozavcodec.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fedb790000",
      |      "code_id": "5B2BB2E141000",
      |      "debug_file": "mozavutil.pdb",
      |      "debug_id": "0E20572BC8A84548B1A8EBE1CEF4215B2",
      |      "end_addr": "0x7fedb7d1000",
      |      "filename": "mozavutil.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fedb8d0000",
      |      "code_id": "5B2BAB8D75000",
      |      "debug_file": "freebl3.pdb",
      |      "debug_id": "C3D7D0D7CAAF418E93D59C39B00CE5031",
      |      "end_addr": "0x7fedb945000",
      |      "filename": "freebl3.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fedb950000",
      |      "code_id": "5B2BAB7531000",
      |      "debug_file": "softokn3.pdb",
      |      "debug_id": "5E851032B61641D5B3866E6AE7C2C1871",
      |      "end_addr": "0x7fedb981000",
      |      "filename": "softokn3.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fedb990000",
      |      "code_id": "57603C879f000",
      |      "debug_file": "EVR.pdb",
      |      "debug_id": "F43C2BBBCC1A4AA086AD5254217329C62",
      |      "end_addr": "0x7fedba2f000",
      |      "filename": "evr.dll",
      |      "version": "6.1.7601.23471"
      |    }, {
      |      "base_addr": "0x7fedba30000",
      |      "code_id": "4A5BDF3321000",
      |      "debug_file": "dxva2.pdb",
      |      "debug_id": "24D2236B7D3E4A1B9CB9CCDD522A956D2",
      |      "end_addr": "0x7fedba51000",
      |      "filename": "dxva2.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fedba60000",
      |      "code_id": "5AFA57BC3f1000",
      |      "debug_file": "mf.pdb",
      |      "debug_id": "8F9542DE81CE4A07BAC8C3605348AB502",
      |      "end_addr": "0x7fedbe51000",
      |      "filename": "mf.dll",
      |      "version": "12.0.7601.24146"
      |    }, {
      |      "base_addr": "0x7fedbe60000",
      |      "code_id": "5A26D96C9c000",
      |      "debug_file": "mscms.pdb",
      |      "debug_id": "94932ADF9263464E9EE7A1C95799641C2",
      |      "end_addr": "0x7fedbefc000",
      |      "filename": "mscms.dll",
      |      "version": "6.1.7601.23971"
      |    }, {
      |      "base_addr": "0x7fedbf00000",
      |      "code_id": "528BF8223c4000",
      |      "debug_file": "d2d1.pdb",
      |      "debug_id": "F389E0D117994CE4AAD29C0638B6A0581",
      |      "end_addr": "0x7fedc2c4000",
      |      "filename": "d2d1.dll",
      |      "version": "6.2.9200.16765"
      |    }, {
      |      "base_addr": "0x7fedc2d0000",
      |      "code_id": "50C1EDA06f000",
      |      "debug_file": "Wpc.pdb",
      |      "debug_id": "E4FDEC6AD0894EDABF0055D257C8B20F2",
      |      "end_addr": "0x7fedc33f000",
      |      "filename": "Wpc.dll",
      |      "version": "1.0.0.1"
      |    }, {
      |      "base_addr": "0x7fedc340000",
      |      "code_id": "590E23BE197000",
      |      "debug_file": "DWrite.pdb",
      |      "debug_id": "AA117027639546889907463815EA51911",
      |      "end_addr": "0x7fedc4d7000",
      |      "filename": "DWrite.dll",
      |      "version": "6.2.9200.22164"
      |    }, {
      |      "base_addr": "0x7fedc4e0000",
      |      "code_id": "5B2BB7EA47b7000",
      |      "debug_file": "xul.pdb",
      |      "debug_id": "82DFF68177AA4FBA89110366462B60342",
      |      "end_addr": "0x7fee0c97000",
      |      "filename": "xul.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fee0ca0000",
      |      "code_id": "5B2BB29Cb1000",
      |      "debug_file": "lgpllibs.pdb",
      |      "debug_id": "8656DE1D92184E35A898159E5AD057412",
      |      "end_addr": "0x7fee0d51000",
      |      "filename": "lgpllibs.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fee0d60000",
      |      "code_id": "5B2BB2F6182000",
      |      "debug_file": "nss3.pdb",
      |      "debug_id": "407D937F331C49239927A96DE663C3EF2",
      |      "end_addr": "0x7fee0ee2000",
      |      "filename": "nss3.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fee0ef0000",
      |      "code_id": "8AC9F9D4f6000",
      |      "debug_file": "ucrtbase.pdb",
      |      "debug_id": "BD96F71A108CCD3AE7318B6B68DBA1781",
      |      "end_addr": "0x7fee0fe6000",
      |      "filename": "ucrtbase.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee0ff0000",
      |      "code_id": "4CE7C5AC125000",
      |      "debug_file": "dbghelp.pdb",
      |      "debug_id": "094E015761DA454EB641C2328520CD6F2",
      |      "end_addr": "0x7fee1115000",
      |      "filename": "dbghelp.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fee1140000",
      |      "code_id": "2F2069B13000",
      |      "debug_file": "api-ms-win-crt-utility-l1-1-0.pdb",
      |      "debug_id": "B48ED637108C6419638D7CC0B543F8741",
      |      "end_addr": "0x7fee1143000",
      |      "filename": "api-ms-win-crt-utility-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1150000",
      |      "code_id": "0FD376E73000",
      |      "debug_file": "api-ms-win-crt-environment-l1-1-0.pdb",
      |      "debug_id": "BB34388344C29BECD3F200FFDF424A3E1",
      |      "end_addr": "0x7fee1153000",
      |      "filename": "api-ms-win-crt-environment-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1160000",
      |      "code_id": "5E6166C23000",
      |      "debug_file": "api-ms-win-crt-filesystem-l1-1-0.pdb",
      |      "debug_id": "452087CB790A1F520CAD137E8BB7857A1",
      |      "end_addr": "0x7fee1163000",
      |      "filename": "api-ms-win-crt-filesystem-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1170000",
      |      "code_id": "7446CA763000",
      |      "debug_file": "api-ms-win-crt-time-l1-1-0.pdb",
      |      "debug_id": "794BB8FF83E445D151E769A381B7941D1",
      |      "end_addr": "0x7fee1173000",
      |      "filename": "api-ms-win-crt-time-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1180000",
      |      "code_id": "ADEE28E85000",
      |      "debug_file": "api-ms-win-crt-multibyte-l1-1-0.pdb",
      |      "debug_id": "79A5A02E087109AE96E8555D41E6D8A21",
      |      "end_addr": "0x7fee1185000",
      |      "filename": "api-ms-win-crt-multibyte-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1190000",
      |      "code_id": "40DDF82E5000",
      |      "debug_file": "api-ms-win-crt-math-l1-1-0.pdb",
      |      "debug_id": "7BFDC0E56B0EA8E3379C01311552B8091",
      |      "end_addr": "0x7fee1195000",
      |      "filename": "api-ms-win-crt-math-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee11a0000",
      |      "code_id": "5A39FEF7a7000",
      |      "debug_file": "msvcp140.amd64.pdb",
      |      "debug_id": "297B9AB89CBB477087A7B97D609489B81",
      |      "end_addr": "0x7fee1247000",
      |      "filename": "msvcp140.dll",
      |      "version": "14.13.26020.0"
      |    }, {
      |      "base_addr": "0x7fee1550000",
      |      "code_id": "30D5EA154000",
      |      "debug_file": "api-ms-win-crt-convert-l1-1-0.pdb",
      |      "debug_id": "AEA6D4FD6883684755FBF72B2DE02CC11",
      |      "end_addr": "0x7fee1554000",
      |      "filename": "api-ms-win-crt-convert-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1560000",
      |      "code_id": "468CA2633000",
      |      "debug_file": "api-ms-win-crt-locale-l1-1-0.pdb",
      |      "debug_id": "09067C948C9C656441EDED91A683C7DB1",
      |      "end_addr": "0x7fee1563000",
      |      "filename": "api-ms-win-crt-locale-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1570000",
      |      "code_id": "1AB62EF83000",
      |      "debug_file": "api-ms-win-crt-heap-l1-1-0.pdb",
      |      "debug_id": "11F739E738D4012148A78F40723925611",
      |      "end_addr": "0x7fee1573000",
      |      "filename": "api-ms-win-crt-heap-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1580000",
      |      "code_id": "CDE986C74000",
      |      "debug_file": "api-ms-win-crt-stdio-l1-1-0.pdb",
      |      "debug_id": "7A34BDD47224CBF921FC3FE25F9BD3011",
      |      "end_addr": "0x7fee1584000",
      |      "filename": "api-ms-win-crt-stdio-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1590000",
      |      "code_id": "5B2BB29235000",
      |      "debug_file": "mozglue.pdb",
      |      "debug_id": "2F3ECDE661F840848A61D35B7C7604BF2",
      |      "end_addr": "0x7fee15c5000",
      |      "filename": "mozglue.dll",
      |      "version": "61.0.0.6746"
      |    }, {
      |      "base_addr": "0x7fee15d0000",
      |      "code_id": "C8B49BD44000",
      |      "debug_file": "api-ms-win-crt-string-l1-1-0.pdb",
      |      "debug_id": "A6F47720BADB15925429C4576FEE27931",
      |      "end_addr": "0x7fee15d4000",
      |      "filename": "api-ms-win-crt-string-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee15e0000",
      |      "code_id": "9717B3D33000",
      |      "debug_file": "api-ms-win-core-file-l1-2-0.pdb",
      |      "debug_id": "7B1D621329FC6691875F35B3D948B7461",
      |      "end_addr": "0x7fee15e3000",
      |      "filename": "api-ms-win-core-file-l1-2-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee15f0000",
      |      "code_id": "523906073000",
      |      "debug_file": "api-ms-win-core-processthreads-l1-1-1.pdb",
      |      "debug_id": "EC0E228D5A163DD73E8E1BC6D73309F11",
      |      "end_addr": "0x7fee15f3000",
      |      "filename": "api-ms-win-core-processthreads-l1-1-1.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee1600000",
      |      "code_id": "3CF8D31B3000",
      |      "debug_file": "api-ms-win-core-localization-l1-2-0.pdb",
      |      "debug_id": "074552F3661C2244FB32B0EB1A7A59891",
      |      "end_addr": "0x7fee1603000",
      |      "filename": "api-ms-win-core-localization-l1-2-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee8070000",
      |      "code_id": "51B8BE533000",
      |      "debug_file": "api-ms-win-core-synch-l1-2-0.pdb",
      |      "debug_id": "1DE3480887A62950ECD73A7B25B06A4B1",
      |      "end_addr": "0x7fee8073000",
      |      "filename": "api-ms-win-core-synch-l1-2-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fee8370000",
      |      "code_id": "5A39FEE316000",
      |      "debug_file": "vcruntime140.amd64.pdb",
      |      "debug_id": "E0AF49B371FD45C2801AFE6F5B029BF31",
      |      "end_addr": "0x7fee8386000",
      |      "filename": "VCRUNTIME140.dll",
      |      "version": "14.13.26020.0"
      |    }, {
      |      "base_addr": "0x7feefc10000",
      |      "code_id": "57603C1B6d000",
      |      "debug_file": "MFPLAT.pdb",
      |      "debug_id": "E7254D6D268543929175E95B04FBCD072",
      |      "end_addr": "0x7feefc7d000",
      |      "filename": "mfplat.dll",
      |      "version": "12.0.7601.23471"
      |    }, {
      |      "base_addr": "0x7fef2940000",
      |      "code_id": "57603BF44f000",
      |      "debug_file": "AudioSes.pdb",
      |      "debug_id": "B996D405E2EA466EA241129B787086712",
      |      "end_addr": "0x7fef298f000",
      |      "filename": "AudioSes.dll",
      |      "version": "6.1.7601.23471"
      |    }, {
      |      "base_addr": "0x7fef3190000",
      |      "code_id": "251C9AEC3000",
      |      "debug_file": "api-ms-win-core-file-l2-1-0.pdb",
      |      "debug_id": "9172B0E9C5E5C5CACD84F10C1EEA278B1",
      |      "end_addr": "0x7fef3193000",
      |      "filename": "api-ms-win-core-file-l2-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fef31a0000",
      |      "code_id": "47381A283000",
      |      "debug_file": "api-ms-win-core-timezone-l1-1-0.pdb",
      |      "debug_id": "76CA285CFEF0038FF1B50FE948752F131",
      |      "end_addr": "0x7fef31a3000",
      |      "filename": "api-ms-win-core-timezone-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fef31b0000",
      |      "code_id": "BE0C9F884000",
      |      "debug_file": "api-ms-win-crt-runtime-l1-1-0.pdb",
      |      "debug_id": "040DB642F32671979B3F00A096DA86631",
      |      "end_addr": "0x7fef31b4000",
      |      "filename": "api-ms-win-crt-runtime-l1-1-0.dll",
      |      "version": "10.0.15063.674"
      |    }, {
      |      "base_addr": "0x7fef3b70000",
      |      "code_id": "4F0CB65C974000",
      |      "debug_file": "igd10umd64.pdb",
      |      "debug_id": "3620082A130F4ACAA3BBA4B6A618EA521",
      |      "end_addr": "0x7fef44e4000",
      |      "filename": "igd10umd64.dll",
      |      "version": "8.15.10.2622"
      |    }, {
      |      "base_addr": "0x7fef5ad0000",
      |      "code_id": "5153B56B1d5000",
      |      "debug_file": "d3d11.pdb",
      |      "debug_id": "3ABF1DF9355C4FF69983BB73D2411E6C1",
      |      "end_addr": "0x7fef5ca5000",
      |      "filename": "d3d11.dll",
      |      "version": "6.2.9200.16570"
      |    }, {
      |      "base_addr": "0x7fef5d00000",
      |      "code_id": "50F30FBD5d000",
      |      "debug_file": "dxgi.pdb",
      |      "debug_id": "D45FA825FC2E48409308A90E23CB16BD1",
      |      "end_addr": "0x7fef5d5d000",
      |      "filename": "dxgi.dll",
      |      "version": "6.2.9200.16492"
      |    }, {
      |      "base_addr": "0x7fef6d30000",
      |      "code_id": "4A5BDFC615000",
      |      "debug_file": "NapiNSP.pdb",
      |      "debug_id": "D77ABF6D8C4643D6A4A721AA53347ECB2",
      |      "end_addr": "0x7fef6d45000",
      |      "filename": "NapiNSP.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef6d60000",
      |      "code_id": "4A5BE0C19000",
      |      "debug_file": "wsock32.pdb",
      |      "debug_id": "FF4403423D314B78AC3A5EB71E5DD9C62",
      |      "end_addr": "0x7fef6d69000",
      |      "filename": "wsock32.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef6e00000",
      |      "code_id": "4A5BE04E19000",
      |      "debug_file": "pnrpnsp.pdb",
      |      "debug_id": "7346E3B8316541FEB959B0787F85CC9A2",
      |      "end_addr": "0x7fef6e19000",
      |      "filename": "pnrpnsp.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef6e90000",
      |      "code_id": "4A5BE0B3b000",
      |      "debug_file": "winrnr.pdb",
      |      "debug_id": "092077177DFC4B37BD49E938377C81392",
      |      "end_addr": "0x7fef6e9b000",
      |      "filename": "winrnr.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef6f50000",
      |      "code_id": "4C9927802e000",
      |      "debug_file": "wlidNSP.pdb",
      |      "debug_id": "EB02DEA5B8604A4396E1E1FB4BB9390F1",
      |      "end_addr": "0x7fef6f7e000",
      |      "filename": "WLIDNSP.DLL",
      |      "version": "7.250.4225.0"
      |    }, {
      |      "base_addr": "0x7fef6f80000",
      |      "code_id": "4CE7CA3D10000",
      |      "debug_file": "wshbth.pdb",
      |      "debug_id": "514520703BE24910A481663D7AE93AF01",
      |      "end_addr": "0x7fef6f90000",
      |      "filename": "wshbth.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fef70d0000",
      |      "code_id": "4A5BDFE67000",
      |      "debug_file": "msimg32.pdb",
      |      "debug_id": "412FBF9A65E64A2DBC04B7F2416B439E2",
      |      "end_addr": "0x7fef70d7000",
      |      "filename": "msimg32.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef8760000",
      |      "code_id": "4DF9970535000",
      |      "debug_file": "XmlLite.pdb",
      |      "debug_id": "83E39FAC2FA24F60A1D10B579152811E2",
      |      "end_addr": "0x7fef8795000",
      |      "filename": "xmllite.dll",
      |      "version": "1.3.1001.0"
      |    }, {
      |      "base_addr": "0x7fef8840000",
      |      "code_id": "4A5BE063b000",
      |      "debug_file": "slc.pdb",
      |      "debug_id": "BEC5C21CFFE144E48E70042F52FF1D271",
      |      "end_addr": "0x7fef884b000",
      |      "filename": "slc.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef8850000",
      |      "code_id": "559EB4FE18000",
      |      "debug_file": "dwmapi.pdb",
      |      "debug_id": "2EDA4EA199544286B7BDADB5D2870F482",
      |      "end_addr": "0x7fef8868000",
      |      "filename": "dwmapi.dll",
      |      "version": "6.1.7601.18917"
      |    }, {
      |      "base_addr": "0x7fef88f0000",
      |      "code_id": "4A5BDF19b000",
      |      "debug_file": "hid.pdb",
      |      "debug_id": "E94CF8FFE34D4948A59D5217186865412",
      |      "end_addr": "0x7fef88fb000",
      |      "filename": "hid.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef8900000",
      |      "code_id": "5A499A7015000",
      |      "debug_file": "nlaapi.pdb",
      |      "debug_id": "FA00D056AA854553BBD394BC73378BDD2",
      |      "end_addr": "0x7fef8915000",
      |      "filename": "nlaapi.dll",
      |      "version": "6.1.7601.24000"
      |    }, {
      |      "base_addr": "0x7fef8b40000",
      |      "code_id": "4A5BDE7019000",
      |      "debug_file": "atl.pdb",
      |      "debug_id": "2139FA2F67DA440AADC70DD8247886F42",
      |      "end_addr": "0x7fef8b59000",
      |      "filename": "atl.dll",
      |      "version": "3.5.2284.0"
      |    }, {
      |      "base_addr": "0x7fef9110000",
      |      "code_id": "4A5BE09356000",
      |      "debug_file": "UxTheme.pdb",
      |      "debug_id": "B0692F27B52C454FACA7380C075DE8632",
      |      "end_addr": "0x7fef9166000",
      |      "filename": "uxtheme.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef9170000",
      |      "code_id": "4A5BDE969000",
      |      "debug_file": "avrt.pdb",
      |      "debug_id": "440A2C0482EC44749E7857447B6350B02",
      |      "end_addr": "0x7fef9179000",
      |      "filename": "avrt.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef92d0000",
      |      "code_id": "4CE7C94A12c000",
      |      "debug_file": "propsys.pdb",
      |      "debug_id": "DF712CBFA5AB4DF1A5D17917B6F8F53F2",
      |      "end_addr": "0x7fef93fc000",
      |      "filename": "propsys.dll",
      |      "version": "7.0.7601.17514"
      |    }, {
      |      "base_addr": "0x7fef9400000",
      |      "code_id": "4A5BDF684b000",
      |      "debug_file": "MMDevAPI.pdb",
      |      "debug_id": "9DA7A0CA9DA445CFB6DE7711ACB9D7D92",
      |      "end_addr": "0x7fef944b000",
      |      "filename": "MMDevAPI.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef9460000",
      |      "code_id": "4A5BE0622c000",
      |      "debug_file": "powrprof.pdb",
      |      "debug_id": "C39D0C69BF9C4D30B1579F2C269CDD732",
      |      "end_addr": "0x7fef948c000",
      |      "filename": "powrprof.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fef9f80000",
      |      "code_id": "589C99861d000",
      |      "debug_file": "samlib.pdb",
      |      "debug_id": "738D6CE80CC8495F8281D371E86D05412",
      |      "end_addr": "0x7fef9f9d000",
      |      "filename": "samlib.dll",
      |      "version": "6.1.7601.23677"
      |    }, {
      |      "base_addr": "0x7fefacc0000",
      |      "code_id": "4CE7C96914000",
      |      "debug_file": "samcli.pdb",
      |      "debug_id": "7220362411A84627BF34F6FD6E6222852",
      |      "end_addr": "0x7fefacd4000",
      |      "filename": "samcli.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefb320000",
      |      "code_id": "4CE7C89Cc000",
      |      "debug_file": "netutils.pdb",
      |      "debug_id": "E65362A1EA9E494A85A124564DE45BB62",
      |      "end_addr": "0x7fefb32c000",
      |      "filename": "netutils.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefb690000",
      |      "code_id": "4A5BDEC618000",
      |      "debug_file": "dhcpcsvc.pdb",
      |      "debug_id": "8DC91C799E404432AB6114D4778D8C5D2",
      |      "end_addr": "0x7fefb6a8000",
      |      "filename": "dhcpcsvc.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefb6b0000",
      |      "code_id": "598D5050b000",
      |      "debug_file": "winnsi.pdb",
      |      "debug_id": "90640512F7794D40AAAB91AACD55CE0C2",
      |      "end_addr": "0x7fefb6bb000",
      |      "filename": "winnsi.dll",
      |      "version": "6.1.7601.23889"
      |    }, {
      |      "base_addr": "0x7fefb6c0000",
      |      "code_id": "4CE7C6DA27000",
      |      "debug_file": "iphlpapi.pdb",
      |      "debug_id": "8B5A2D4E17EF425EB27B7705D3BC92C72",
      |      "end_addr": "0x7fefb6e7000",
      |      "filename": "IPHLPAPI.DLL",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefb6f0000",
      |      "code_id": "4A5BE0B77000",
      |      "debug_file": "wshtcpip.pdb",
      |      "debug_id": "CDA0B508B8854B9A929B4B8B06A1B3852",
      |      "end_addr": "0x7fefb6f7000",
      |      "filename": "WSHTCPIP.DLL",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefc0e0000",
      |      "code_id": "4A5BE03D2d000",
      |      "debug_file": "ntmarta.pdb",
      |      "debug_id": "60A5AD5FC924461CBB5A1076A8ECCDEE2",
      |      "end_addr": "0x7fefc10d000",
      |      "filename": "ntmarta.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefc3b0000",
      |      "code_id": "4A5BE082c000",
      |      "debug_file": "version.pdb",
      |      "debug_id": "F7695888ADD14D7793D5425AC6717D012",
      |      "end_addr": "0x7fefc3bc000",
      |      "filename": "version.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefc440000",
      |      "code_id": "4A5BE0B03b000",
      |      "debug_file": "winmm.pdb",
      |      "debug_id": "DF6E9D2411CE4DDFB398C369D05BC96B2",
      |      "end_addr": "0x7fefc47b000",
      |      "filename": "winmm.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefc540000",
      |      "code_id": "4A5BE09C11000",
      |      "debug_file": "wtsapi32.pdb",
      |      "debug_id": "664648C96F374FD2A0B0EEE2284238772",
      |      "end_addr": "0x7fefc551000",
      |      "filename": "wtsapi32.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefce20000",
      |      "code_id": "4D6F30F15b000",
      |      "debug_file": "dnsapi.pdb",
      |      "debug_id": "94B9DFED96E6487EA1773F4814606DC32",
      |      "end_addr": "0x7fefce7b000",
      |      "filename": "dnsapi.dll",
      |      "version": "6.1.7601.17570"
      |    }, {
      |      "base_addr": "0x7fefcfa0000",
      |      "code_id": "573365E855000",
      |      "debug_file": "mswsock.pdb",
      |      "debug_id": "E9D5829A6D294676ABE83EDEB0FE8BEA2",
      |      "end_addr": "0x7fefcff5000",
      |      "filename": "mswsock.dll",
      |      "version": "6.1.7601.23451"
      |    }, {
      |      "base_addr": "0x7fefd150000",
      |      "code_id": "5B0CBBBC22000",
      |      "debug_file": "bcrypt.pdb",
      |      "debug_id": "E344332D0014476DBA7AE0D84C8FB9A02",
      |      "end_addr": "0x7fefd172000",
      |      "filename": "bcrypt.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7fefd210000",
      |      "code_id": "4A5BE0876d000",
      |      "debug_file": "wevtapi.pdb",
      |      "debug_id": "C6F871C3F5764951B2599519004E33152",
      |      "end_addr": "0x7fefd27d000",
      |      "filename": "wevtapi.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefd5e0000",
      |      "code_id": "5B0CBBF425000",
      |      "debug_file": "sspicli.pdb",
      |      "debug_id": "4DF0E8B83E354367AABEA63457DB69C52",
      |      "end_addr": "0x7fefd605000",
      |      "filename": "sspicli.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7fefd670000",
      |      "code_id": "5B0CBCA2f000",
      |      "debug_file": "cryptbase.pdb",
      |      "debug_id": "F291DE80684E4F7F8A66976AC3DB54FF2",
      |      "end_addr": "0x7fefd67f000",
      |      "filename": "CRYPTBASE.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7fefd810000",
      |      "code_id": "4A5BE01Ff000",
      |      "debug_file": "profapi.pdb",
      |      "debug_id": "DC86D275BBB0437F94B2CD4B06D4B9342",
      |      "end_addr": "0x7fefd81f000",
      |      "filename": "profapi.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefd820000",
      |      "code_id": "4CE7C7C5f000",
      |      "debug_file": "msasn1.pdb",
      |      "debug_id": "E973C31CB03943DCAF30541CBAEB9A052",
      |      "end_addr": "0x7fefd82f000",
      |      "filename": "msasn1.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefd840000",
      |      "code_id": "5A26D97F3b000",
      |      "debug_file": "wintrust.pdb",
      |      "debug_id": "4D7A4677D86B4B9DB1C38B9EDBF03B062",
      |      "end_addr": "0x7fefd87b000",
      |      "filename": "wintrust.dll",
      |      "version": "6.1.7601.23971"
      |    }, {
      |      "base_addr": "0x7fefd890000",
      |      "code_id": "4CE7C55C36000",
      |      "debug_file": "cfgmgr32.pdb",
      |      "debug_id": "9FA5840A4D3C418799F62164E98C9DE82",
      |      "end_addr": "0x7fefd8c6000",
      |      "filename": "cfgmgr32.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefd970000",
      |      "code_id": "5A26DA1016d000",
      |      "debug_file": "crypt32.pdb",
      |      "debug_id": "710C2BA4222D4EAF86CBB1A0547971D62",
      |      "end_addr": "0x7fefdadd000",
      |      "filename": "crypt32.dll",
      |      "version": "6.1.7601.23971"
      |    }, {
      |      "base_addr": "0x7fefdb10000",
      |      "code_id": "4CE7C9F41e000",
      |      "debug_file": "userenv.pdb",
      |      "debug_id": "9279A3188C0745E88BB89DFBD6B071062",
      |      "end_addr": "0x7fefdb2e000",
      |      "filename": "userenv.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefdb30000",
      |      "code_id": "5B0CBC656a000",
      |      "debug_file": "kernelbase.pdb",
      |      "debug_id": "C357DF0380104CEDA9C5CAE4FFB2D32A2",
      |      "end_addr": "0x7fefdb9a000",
      |      "filename": "KERNELBASE.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7fefdbb0000",
      |      "code_id": "4A5BDEE11a000",
      |      "debug_file": "devobj.pdb",
      |      "debug_id": "80698D17FF4B4B7890DCC6322480A7F42",
      |      "end_addr": "0x7fefdbca000",
      |      "filename": "devobj.dll",
      |      "version": "6.1.7600.16385"
      |    }, {
      |      "base_addr": "0x7fefdbd0000",
      |      "code_id": "4A5BDEBA99000",
      |      "debug_file": "CLBCatQ.pdb",
      |      "debug_id": "60B9D310C472440BA13F66BFF0FC39E32",
      |      "end_addr": "0x7fefdc69000",
      |      "filename": "clbcatq.dll",
      |      "version": "2001.12.8530.16385"
      |    }, {
      |      "base_addr": "0x7fefdc70000",
      |      "code_id": "573365BB4d000",
      |      "debug_file": "ws2_32.pdb",
      |      "debug_id": "5D9C92DA00D24235AD321A8810C80B022",
      |      "end_addr": "0x7fefdcbd000",
      |      "filename": "ws2_32.dll",
      |      "version": "6.1.7601.23451"
      |    }, {
      |      "base_addr": "0x7fefdcc0000",
      |      "code_id": "59B2B7A867000",
      |      "debug_file": "gdi32.pdb",
      |      "debug_id": "8B4199D8D17B433B9D856385003BB3792",
      |      "end_addr": "0x7fefdd27000",
      |      "filename": "gdi32.dll",
      |      "version": "6.1.7601.23914"
      |    }, {
      |      "base_addr": "0x7fefdd30000",
      |      "code_id": "4CE7C9AB71000",
      |      "debug_file": "shlwapi.pdb",
      |      "debug_id": "0820A0750C1A4E2597E17DEA57D049542",
      |      "end_addr": "0x7fefdda1000",
      |      "filename": "shlwapi.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7fefddb0000",
      |      "code_id": "5ADD223D1fd000",
      |      "debug_file": "ole32.pdb",
      |      "debug_id": "27AAB1498C304602AD95C42B0890D89C2",
      |      "end_addr": "0x7fefdfad000",
      |      "filename": "ole32.dll",
      |      "version": "6.1.7601.24117"
      |    }, {
      |      "base_addr": "0x7fefdfb0000",
      |      "code_id": "599464F6cb000",
      |      "debug_file": "usp10.pdb",
      |      "debug_id": "4D353235DC7C421F97B36D0A13612C651",
      |      "end_addr": "0x7fefe07b000",
      |      "filename": "usp10.dll",
      |      "version": "1.626.7601.23894"
      |    }, {
      |      "base_addr": "0x7fefe080000",
      |      "code_id": "4EEB033F9f000",
      |      "debug_file": "msvcrt.pdb",
      |      "debug_id": "5376EFB7D7E54B4DA4156FF73543E6B02",
      |      "end_addr": "0x7fefe11f000",
      |      "filename": "msvcrt.dll",
      |      "version": "7.0.7601.17744"
      |    }, {
      |      "base_addr": "0x7fefe120000",
      |      "code_id": "5A499A78d8b000",
      |      "debug_file": "shell32.pdb",
      |      "debug_id": "74E8D537BE9E4472B07BDDEDE88F328B2",
      |      "end_addr": "0x7fefeeab000",
      |      "filename": "shell32.dll",
      |      "version": "6.1.7601.24000"
      |    }, {
      |      "base_addr": "0x7fefeeb0000",
      |      "code_id": "5ADD2241da000",
      |      "debug_file": "oleaut32.pdb",
      |      "debug_id": "A8707FAA414547BDB8508F8395A407F32",
      |      "end_addr": "0x7fefef8a000",
      |      "filename": "oleaut32.dll",
      |      "version": "6.1.7601.24117"
      |    }, {
      |      "base_addr": "0x7fefef90000",
      |      "code_id": "598D507C52000",
      |      "debug_file": "wldap32.pdb",
      |      "debug_id": "677A0603DC5B48CE8FCEF9BDF58A0F7F2",
      |      "end_addr": "0x7fefefe2000",
      |      "filename": "Wldap32.dll",
      |      "version": "6.1.7601.23889"
      |    }, {
      |      "base_addr": "0x7fefeff0000",
      |      "code_id": "5B0CBBBFdb000",
      |      "debug_file": "advapi32.pdb",
      |      "debug_id": "0CB37DE510414EF488338CD5E8A93DE62",
      |      "end_addr": "0x7feff0cb000",
      |      "filename": "advapi32.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7feff400000",
      |      "code_id": "556367281f000",
      |      "debug_file": "sechost.pdb",
      |      "debug_id": "3824AD19AB6C47AA8870D6E371F1738B1",
      |      "end_addr": "0x7feff41f000",
      |      "filename": "sechost.dll",
      |      "version": "6.1.7601.18869"
      |    }, {
      |      "base_addr": "0x7feff6f0000",
      |      "code_id": "598D50728000",
      |      "debug_file": "nsi.pdb",
      |      "debug_id": "79011FECBE8049D88C2884EF5DAAF3EE2",
      |      "end_addr": "0x7feff6f8000",
      |      "filename": "nsi.dll",
      |      "version": "6.1.7601.23889"
      |    }, {
      |      "base_addr": "0x7feff700000",
      |      "code_id": "4CE7C9A21d7000",
      |      "debug_file": "setupapi.pdb",
      |      "debug_id": "59FE89504C234732A39999043CA804FE2",
      |      "end_addr": "0x7feff8d7000",
      |      "filename": "setupapi.dll",
      |      "version": "6.1.7601.17514"
      |    }, {
      |      "base_addr": "0x7feffaf0000",
      |      "code_id": "5AA2CD72e000",
      |      "debug_file": "lpk.pdb",
      |      "debug_id": "C96F9E3BE4CF41B9831AAA1AC809C8272",
      |      "end_addr": "0x7feffafe000",
      |      "filename": "lpk.dll",
      |      "version": "6.1.7601.24082"
      |    }, {
      |      "base_addr": "0x7feffb00000",
      |      "code_id": "59B94EC5109000",
      |      "debug_file": "msctf.pdb",
      |      "debug_id": "E21456E28F024CC487969536946A89E02",
      |      "end_addr": "0x7feffc09000",
      |      "filename": "msctf.dll",
      |      "version": "6.1.7601.23915"
      |    }, {
      |      "base_addr": "0x7feffcb0000",
      |      "code_id": "5B0CBBC612d000",
      |      "debug_file": "rpcrt4.pdb",
      |      "debug_id": "735F10B8899A47C0A126DA2030952A562",
      |      "end_addr": "0x7feffddd000",
      |      "filename": "rpcrt4.dll",
      |      "version": "6.1.7601.24150"
      |    }, {
      |      "base_addr": "0x7feffe00000",
      |      "code_id": "4A5BDF402e000",
      |      "debug_file": "imm32.pdb",
      |      "debug_id": "98F27BA5AEE541ECBEE00CD03AD50FEE2",
      |      "end_addr": "0x7feffe2e000",
      |      "filename": "imm32.dll",
      |      "version": "6.1.7600.16385"
      |    }],
      |    "status": "OK",
      |    "threads": [{
      |      "frames": [{
      |        "ip": "0x7fefdcc58ca",
      |        "module_index": 96,
      |        "trust": "context"
      |      }, {
      |        "ip": "0x7fefdcc5882",
      |        "module_index": 96,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedd1537cf",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedd15363e",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcdcc39b",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf47d7c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf47c17",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf47bd2",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7feddba76df",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7feddbb4b8b",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf471f6",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf470ce",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcf4784e",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc93d816",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc94184a",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc941e0f",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc94218a",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbdcc26",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc93c1cc",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbda3e6",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbdda48",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbde216",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbebc1d",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb6c7",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb409",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeda09",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01ad",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc999cd2",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcdd28ea",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc999f7d",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbec080",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb6c7",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb409",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeda09",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01ad",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc999cd2",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcdd28ea",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc999f7d",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbec080",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb6c7",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeb409",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbeda09",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01ad",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbee5ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe0941",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe01d8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe187c",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbe2452",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbd75fe",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedd027b8a",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbd75fe",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbd73d3",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbd6e3a",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcbd56ec",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcb996a8",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcb9881b",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcc97716",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcb8f23f",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedcc97c47",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedccec4c1",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc938ef3",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc938f8e",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedc937f09",
      |        "module_index": 20,
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x274936a4018",
      |        "trust": "cfi"
      |      }, {
      |        "ip": "0x7fedf3f1697",
      |        "module_index": 20,
      |        "trust": "scan"
      |      }, {
      |        "ip": "0x7fedfe5960f",
      |        "module_index": 20,
      |        "trust": "scan"
      |      }]
      |    }]
      |  }
    """.stripMargin

  val sampleStackTrace2: String =
    """
      |"stackTraces": {
      |  "crash_info":{
      |    "address":"0x6b0d7be7",
      |    "crashing_thread":0,
      |    "type":"EXCEPTION_BREAKPOINT"
      |  },
      |  "main_module":0,
      |  "modules":[
      |    {
      |      "base_addr":"0x1200000",
      |      "code_id":"5B070FD664000",
      |      "debug_file":"firefox.pdb",
      |      "debug_id":"067CB22E5DAD4D22A98C65DD7993474A2",
      |      "end_addr":"0x1264000",
      |      "filename":"firefox.exe",
      |      "version":"61.0.0.6718"
      |    },
      |    {
      |      "base_addr":"0x672e0000",
      |      "code_id":"5B0714F63c95000",
      |      "debug_file":"xul.pdb",
      |      "debug_id":"8E9E96D5FBE34E298390ACD5A37C0C5C2",
      |      "end_addr":"0x6af75000",
      |      "filename":"xul.dll",
      |      "version":"61.0.0.6718"
      |    },
      |    {
      |      "base_addr":"0x6b0b0000",
      |      "code_id":"4A5BDA0A109000",
      |      "debug_file":"DWrite.pdb",
      |      "debug_id":"1CD85F8C5C6A4CADAAF184A181D1E3261",
      |      "end_addr":"0x6b1b9000",
      |      "filename":"DWrite.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x6b2c0000",
      |      "code_id":"5B07101C13e000",
      |      "debug_file":"nss3.pdb",
      |      "debug_id":"118FA1DC26DA41F382257A1C54D95A522",
      |      "end_addr":"0x6b3fe000",
      |      "filename":"nss3.dll",
      |      "version":"61.0.0.6718"
      |    },
      |    {
      |      "base_addr":"0x6b480000",
      |      "code_id":"5B070FCAaf000",
      |      "debug_file":"lgpllibs.pdb",
      |      "debug_id":"EFC5313BB59E4CA68D1C58D017D5DE082",
      |      "end_addr":"0x6b52f000",
      |      "filename":"lgpllibs.dll",
      |      "version":"61.0.0.6718"
      |    },
      |    {
      |      "base_addr":"0x6b530000",
      |      "code_id":"4A5BDB637000",
      |      "debug_file":"wsock32.pdb",
      |      "debug_id":"0AAE6683A7F540DFB265DCD12B7BD0FB2",
      |      "end_addr":"0x6b537000",
      |      "filename":"wsock32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x6b540000",
      |      "code_id":"A6AF90DD3000",
      |      "debug_file":"api-ms-win-crt-environment-l1-1-0.pdb",
      |      "debug_id":"7E87CC21DE165237381C5C4A952DAB4E1",
      |      "end_addr":"0x6b543000",
      |      "filename":"api-ms-win-crt-environment-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b550000",
      |      "code_id":"34E7A80A3000",
      |      "debug_file":"api-ms-win-crt-utility-l1-1-0.pdb",
      |      "debug_id":"719167FF4A1EAD72BF61C0BAD5F1429F1",
      |      "end_addr":"0x6b553000",
      |      "filename":"api-ms-win-crt-utility-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b560000",
      |      "code_id":"63FFAC073000",
      |      "debug_file":"api-ms-win-crt-time-l1-1-0.pdb",
      |      "debug_id":"21FD7C9F5D7AE8AD2595B37970C481831",
      |      "end_addr":"0x6b563000",
      |      "filename":"api-ms-win-crt-time-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b570000",
      |      "code_id":"AECBAA023000",
      |      "debug_file":"api-ms-win-crt-filesystem-l1-1-0.pdb",
      |      "debug_id":"373FC9DFCDD60085103826E00EADC7CF1",
      |      "end_addr":"0x6b573000",
      |      "filename":"api-ms-win-crt-filesystem-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b580000",
      |      "code_id":"040F85385000",
      |      "debug_file":"api-ms-win-crt-math-l1-1-0.pdb",
      |      "debug_id":"0A9A97CF4A1C1F5EB5DD278A15D0477E1",
      |      "end_addr":"0x6b585000",
      |      "filename":"api-ms-win-crt-math-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b590000",
      |      "code_id":"DCAB0F725000",
      |      "debug_file":"api-ms-win-crt-multibyte-l1-1-0.pdb",
      |      "debug_id":"FB60C252C448F241976FF0BF7FF248411",
      |      "end_addr":"0x6b595000",
      |      "filename":"api-ms-win-crt-multibyte-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b5a0000",
      |      "code_id":"E7679E3E4000",
      |      "debug_file":"api-ms-win-crt-convert-l1-1-0.pdb",
      |      "debug_id":"B1D9A5AFC39F6CAB5EB088F6C5833B311",
      |      "end_addr":"0x6b5a4000",
      |      "filename":"api-ms-win-crt-convert-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b5b0000",
      |      "code_id":"4D75A9EA3000",
      |      "debug_file":"api-ms-win-crt-locale-l1-1-0.pdb",
      |      "debug_id":"65B0F09C2872A77E7440E8647964626F1",
      |      "end_addr":"0x6b5b3000",
      |      "filename":"api-ms-win-crt-locale-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b5c0000",
      |      "code_id":"D8C924C3118000",
      |      "debug_file":"ucrtbase.pdb",
      |      "debug_id":"4E2F1EE562CF4A3ADEC11E5B352608F81",
      |      "end_addr":"0x6b6d8000",
      |      "filename":"ucrtbase.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b6e0000",
      |      "code_id":"5A39FDC071000",
      |      "debug_file":"msvcp140.i386.pdb",
      |      "debug_id":"73FE4B5DE1914144B5554D35A1CA83431",
      |      "end_addr":"0x6b751000",
      |      "filename":"msvcp140.dll",
      |      "version":"14.13.26020.0"
      |    },
      |    {
      |      "base_addr":"0x6b760000",
      |      "code_id":"B7E609C24000",
      |      "debug_file":"api-ms-win-crt-stdio-l1-1-0.pdb",
      |      "debug_id":"36CF36F8E5643D8E29C0026C743B4BF91",
      |      "end_addr":"0x6b764000",
      |      "filename":"api-ms-win-crt-stdio-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b770000",
      |      "code_id":"B9AFA91C3000",
      |      "debug_file":"api-ms-win-crt-heap-l1-1-0.pdb",
      |      "debug_id":"8DEB2ADF6CB1359BE65B8EF72AE900FB1",
      |      "end_addr":"0x6b773000",
      |      "filename":"api-ms-win-crt-heap-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b780000",
      |      "code_id":"DAEC23544000",
      |      "debug_file":"api-ms-win-crt-string-l1-1-0.pdb",
      |      "debug_id":"9001A5202BAF8D8BF42D889A5674EB7B1",
      |      "end_addr":"0x6b784000",
      |      "filename":"api-ms-win-crt-string-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b790000",
      |      "code_id":"8D1F403A3000",
      |      "debug_file":"api-ms-win-core-file-l1-2-0.pdb",
      |      "debug_id":"FC036E58FA7EAF819CFAA813C422759B1",
      |      "end_addr":"0x6b793000",
      |      "filename":"api-ms-win-core-file-l1-2-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b7a0000",
      |      "code_id":"8BD5920F3000",
      |      "debug_file":"api-ms-win-core-processthreads-l1-1-1.pdb",
      |      "debug_id":"08CE348B5935130CCAD8C1BA298AB5461",
      |      "end_addr":"0x6b7a3000",
      |      "filename":"api-ms-win-core-processthreads-l1-1-1.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b7b0000",
      |      "code_id":"F0FBD5853000",
      |      "debug_file":"api-ms-win-core-synch-l1-2-0.pdb",
      |      "debug_id":"18F36B90084DD9D0FE1711CFC1069FC51",
      |      "end_addr":"0x6b7b3000",
      |      "filename":"api-ms-win-core-synch-l1-2-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b7c0000",
      |      "code_id":"1758E0DC3000",
      |      "debug_file":"api-ms-win-core-localization-l1-2-0.pdb",
      |      "debug_id":"E086F143FA71C6BDAA00BED768AEF8601",
      |      "end_addr":"0x6b7c3000",
      |      "filename":"api-ms-win-core-localization-l1-2-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6b7d0000",
      |      "code_id":"E22A68BB3000",
      |      "debug_file":"api-ms-win-core-timezone-l1-1-0.pdb",
      |      "debug_id":"20BD47082FD95E2B831CF66D9CA7AE201",
      |      "end_addr":"0x6b7d3000",
      |      "filename":"api-ms-win-core-timezone-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6ce80000",
      |      "code_id":"2442342C3000",
      |      "debug_file":"api-ms-win-core-file-l2-1-0.pdb",
      |      "debug_id":"B529FEAC0CF82EC2DE8D7CE4BD99EA2D1",
      |      "end_addr":"0x6ce83000",
      |      "filename":"api-ms-win-core-file-l2-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6ce90000",
      |      "code_id":"5B070FBF2a000",
      |      "debug_file":"mozglue.pdb",
      |      "debug_id":"DBC264D31496448983A4A58A6B4EEC282",
      |      "end_addr":"0x6ceba000",
      |      "filename":"mozglue.dll",
      |      "version":"61.0.0.6718"
      |    },
      |    {
      |      "base_addr":"0x6cec0000",
      |      "code_id":"A889ABC34000",
      |      "debug_file":"api-ms-win-crt-runtime-l1-1-0.pdb",
      |      "debug_id":"742CDEFCD3AAB09CFB209D3FB7B95F0E1",
      |      "end_addr":"0x6cec4000",
      |      "filename":"api-ms-win-crt-runtime-l1-1-0.dll",
      |      "version":"10.0.15063.674"
      |    },
      |    {
      |      "base_addr":"0x6ced0000",
      |      "code_id":"5A39FDB615000",
      |      "debug_file":"vcruntime140.i386.pdb",
      |      "debug_id":"80840243E3484ACBB73F5740507A7DFC1",
      |      "end_addr":"0x6cee5000",
      |      "filename":"VCRUNTIME140.dll",
      |      "version":"14.13.26020.0"
      |    },
      |    {
      |      "base_addr":"0x6f790000",
      |      "code_id":"4A5BDAA05000",
      |      "debug_file":"msimg32.pdb",
      |      "debug_id":"13C89B70614E4E7FB1C677D443D15D182",
      |      "end_addr":"0x6f795000",
      |      "filename":"msimg32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x6f820000",
      |      "code_id":"4A5BDB4232000",
      |      "debug_file":"winmm.pdb",
      |      "debug_id":"76D9200CF4D84D759AE6370C1853BD0A2",
      |      "end_addr":"0x6f852000",
      |      "filename":"winmm.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x71ab0000",
      |      "code_id":"4A5BD9B512000",
      |      "debug_file":"dhcpcsvc.pdb",
      |      "debug_id":"D3E01A80603143AC83EA5801BEC674D82",
      |      "end_addr":"0x71ac2000",
      |      "filename":"dhcpcsvc.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x72550000",
      |      "code_id":"4A5BD9B2eb000",
      |      "debug_file":"dbghelp.pdb",
      |      "debug_id":"44CEF9DBD9F04BB08B6DD16C1D8EA04B2",
      |      "end_addr":"0x7263b000",
      |      "filename":"dbghelp.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x72670000",
      |      "code_id":"4A5BDAF612000",
      |      "debug_file":"pnrpnsp.pdb",
      |      "debug_id":"D02619BD04DB444D9215D3CE9D69F1062",
      |      "end_addr":"0x72682000",
      |      "filename":"pnrpnsp.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x731a0000",
      |      "code_id":"4A5BDA1B1c000",
      |      "debug_file":"iphlpapi.pdb",
      |      "debug_id":"C2C3D877E86D4113B7464B01958803832",
      |      "end_addr":"0x731bc000",
      |      "filename":"IPHLPAPI.DLL",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x731c0000",
      |      "code_id":"4A5BDB437000",
      |      "debug_file":"winnsi.pdb",
      |      "debug_id":"F7A0C25B1B0E4FA6876494D3F6BFD1012",
      |      "end_addr":"0x731c7000",
      |      "filename":"winnsi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x733f0000",
      |      "code_id":"4A5BDA6D10000",
      |      "debug_file":"NapiNSP.pdb",
      |      "debug_id":"35D1FB446B064C3C84108202E55B284A2",
      |      "end_addr":"0x73400000",
      |      "filename":"NapiNSP.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x73400000",
      |      "code_id":"4A5BDB448000",
      |      "debug_file":"winrnr.pdb",
      |      "debug_id":"45F8B97B128548D788289C58E8C5B3CA2",
      |      "end_addr":"0x73408000",
      |      "filename":"winrnr.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x73640000",
      |      "code_id":"4A5BDB43d000",
      |      "debug_file":"wtsapi32.pdb",
      |      "debug_id":"B71F6DE61001410FA7DE98BBB176C8DE2",
      |      "end_addr":"0x7364d000",
      |      "filename":"wtsapi32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x73790000",
      |      "code_id":"4A5BDA0713000",
      |      "debug_file":"dwmapi.pdb",
      |      "debug_id":"D8D91B3F339A4FDC960FC7121D146DF42",
      |      "end_addr":"0x737a3000",
      |      "filename":"dwmapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x737c0000",
      |      "code_id":"4A5BD9EC9000",
      |      "debug_file":"hid.pdb",
      |      "debug_id":"015645EF10DE49B084EE745A3FDC0F8D2",
      |      "end_addr":"0x737c9000",
      |      "filename":"hid.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x73a90000",
      |      "code_id":"4A5BDB3840000",
      |      "debug_file":"UxTheme.pdb",
      |      "debug_id":"5BECAB35E7714835A6BF3DADD891BB3A2",
      |      "end_addr":"0x73ad0000",
      |      "filename":"uxtheme.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x73fc0000",
      |      "code_id":"4A5BDA7C10000",
      |      "debug_file":"nlaapi.pdb",
      |      "debug_id":"4E6F29B512CC4375B2D4AEFDD430D15E2",
      |      "end_addr":"0x73fd0000",
      |      "filename":"nlaapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74130000",
      |      "code_id":"4A5BDAE921000",
      |      "debug_file":"ntmarta.pdb",
      |      "debug_id":"654E30BBAE864799893AF657FEA771722",
      |      "end_addr":"0x74151000",
      |      "filename":"ntmarta.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74180000",
      |      "code_id":"4A5BD9987000",
      |      "debug_file":"avrt.pdb",
      |      "debug_id":"A15E148ACFC74F88A0A6DAF71CC916472",
      |      "end_addr":"0x74187000",
      |      "filename":"avrt.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74490000",
      |      "code_id":"4A5BDB2B9000",
      |      "debug_file":"version.pdb",
      |      "debug_id":"52234E5C7EC44646B62D56357B2C94872",
      |      "end_addr":"0x74499000",
      |      "filename":"version.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74520000",
      |      "code_id":"4A5BDB5A5000",
      |      "debug_file":"wshtcpip.pdb",
      |      "debug_id":"92A92FF293574B489795898561F007192",
      |      "end_addr":"0x74525000",
      |      "filename":"WSHTCPIP.DLL",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x745f0000",
      |      "code_id":"4A5BDB3117000",
      |      "debug_file":"userenv.pdb",
      |      "debug_id":"EA497D1A81EE45B59922DB0889F64DB12",
      |      "end_addr":"0x74607000",
      |      "filename":"userenv.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x748b0000",
      |      "code_id":"4A5BD9D944000",
      |      "debug_file":"dnsapi.pdb",
      |      "debug_id":"5DDB8C6479B8497CBD7E91AB75C1B6272",
      |      "end_addr":"0x748f4000",
      |      "filename":"dnsapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x749f0000",
      |      "code_id":"4A5BDA773c000",
      |      "debug_file":"mswsock.pdb",
      |      "debug_id":"76A0D89DF9784418A141841FBB52B23A2",
      |      "end_addr":"0x74a2c000",
      |      "filename":"mswsock.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74e70000",
      |      "code_id":"4A5BDB3C1a000",
      |      "debug_file":"sspicli.pdb",
      |      "debug_id":"79FF79C8BA3E49F3AC1AAA7D6E8011EF2",
      |      "end_addr":"0x74e8a000",
      |      "filename":"sspicli.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74ee0000",
      |      "code_id":"4A5BBF41c000",
      |      "debug_file":"cryptbase.pdb",
      |      "debug_id":"E62FEAE559EE4CD995614215B01AC2102",
      |      "end_addr":"0x74eec000",
      |      "filename":"CRYPTBASE.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x74f90000",
      |      "code_id":"4A5BBF41b000",
      |      "debug_file":"profapi.pdb",
      |      "debug_id":"CA045CA9E5C74D899B90E776B38A18B32",
      |      "end_addr":"0x74f9b000",
      |      "filename":"profapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75000000",
      |      "code_id":"4A5BDA60c000",
      |      "debug_file":"msasn1.pdb",
      |      "debug_id":"919494139BC7451EA7025ADA442D722A2",
      |      "end_addr":"0x7500c000",
      |      "filename":"msasn1.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75010000",
      |      "code_id":"4A5BDAAE4a000",
      |      "debug_file":"kernelbase.pdb",
      |      "debug_id":"59D5EEBCB6B044C7A1572DAE49752E1D2",
      |      "end_addr":"0x7505a000",
      |      "filename":"KERNELBASE.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75060000",
      |      "code_id":"4A5BD9A627000",
      |      "debug_file":"cfgmgr32.pdb",
      |      "debug_id":"8E6E976F862B40D59BA4AFFE0921744C2",
      |      "end_addr":"0x75087000",
      |      "filename":"cfgmgr32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75120000",
      |      "code_id":"4A5BDA3911c000",
      |      "debug_file":"crypt32.pdb",
      |      "debug_id":"34F927A5A57245B5AE51B87DA23FA4AC2",
      |      "end_addr":"0x7523c000",
      |      "filename":"crypt32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75240000",
      |      "code_id":"4A5BD9C512000",
      |      "debug_file":"devobj.pdb",
      |      "debug_id":"5EC40EC65C5045EB96F4CFDCFC6E19062",
      |      "end_addr":"0x75252000",
      |      "filename":"devobj.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75260000",
      |      "code_id":"4A5BDB522d000",
      |      "debug_file":"wintrust.pdb",
      |      "debug_id":"8EF9EC5AF2FF4A7294E2069543612E9B2",
      |      "end_addr":"0x7528d000",
      |      "filename":"wintrust.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75390000",
      |      "code_id":"4A5BDB329d000",
      |      "debug_file":"usp10.pdb",
      |      "debug_id":"0F136332ED524622ACD511B27629058A1",
      |      "end_addr":"0x7542d000",
      |      "filename":"usp10.dll",
      |      "version":"1.626.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75430000",
      |      "code_id":"4A5BDB2Fc9000",
      |      "debug_file":"user32.pdb",
      |      "debug_id":"C1D1D6EB9354465389912A697CCB2D502",
      |      "end_addr":"0x754f9000",
      |      "filename":"user32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75500000",
      |      "code_id":"4A5BDB4A35000",
      |      "debug_file":"ws2_32.pdb",
      |      "debug_id":"05B47564705B4BB0BFD23EEDD39091922",
      |      "end_addr":"0x75535000",
      |      "filename":"ws2_32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75540000",
      |      "code_id":"4A5BDADEa1000",
      |      "debug_file":"rpcrt4.pdb",
      |      "debug_id":"20EE55884BAB426589CA1892F8B9003C2",
      |      "end_addr":"0x755e1000",
      |      "filename":"rpcrt4.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75620000",
      |      "code_id":"4A5BDA071f000",
      |      "debug_file":"imm32.pdb",
      |      "debug_id":"105C90B10D924E02AE71D8ECCE77CDC62",
      |      "end_addr":"0x7563f000",
      |      "filename":"imm32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75640000",
      |      "code_id":"4A5BDAADd4000",
      |      "debug_file":"kernel32.pdb",
      |      "debug_id":"1BA64A33475D4E37AFA2653A76541B8B2",
      |      "end_addr":"0x75714000",
      |      "filename":"kernel32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75920000",
      |      "code_id":"4A5BDA6Fac000",
      |      "debug_file":"msvcrt.pdb",
      |      "debug_id":"6EC79267530C45188F2A816AD59DBBF92",
      |      "end_addr":"0x759cc000",
      |      "filename":"msvcrt.dll",
      |      "version":"7.0.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75b10000",
      |      "code_id":"4A5BDAFE19d000",
      |      "debug_file":"setupapi.pdb",
      |      "debug_id":"F7D7468DFCD247CAA97C8C8BD291E1492",
      |      "end_addr":"0x75cad000",
      |      "filename":"setupapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75cb0000",
      |      "code_id":"4A5BDB6245000",
      |      "debug_file":"wldap32.pdb",
      |      "debug_id":"73663B9E1D7A4D838A24EE4D1B5B8CAD2",
      |      "end_addr":"0x75cf5000",
      |      "filename":"Wldap32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75d00000",
      |      "code_id":"4A5BDB0557000",
      |      "debug_file":"shlwapi.pdb",
      |      "debug_id":"372BB4590B784DBDA605154B826C29EB2",
      |      "end_addr":"0x75d57000",
      |      "filename":"shlwapi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x75d60000",
      |      "code_id":"4A5BDB01c49000",
      |      "debug_file":"shell32.pdb",
      |      "debug_id":"E15423F5EFC04E299A6639090E93FCD92",
      |      "end_addr":"0x769a9000",
      |      "filename":"shell32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x769c0000",
      |      "code_id":"4A5BD97Ea0000",
      |      "debug_file":"advapi32.pdb",
      |      "debug_id":"8215E3385BE64C70AD230B20F032B9402",
      |      "end_addr":"0x76a60000",
      |      "filename":"advapi32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76a60000",
      |      "code_id":"4A5BDACA8f000",
      |      "debug_file":"oleaut32.pdb",
      |      "debug_id":"204621952AB4418390863F295E593B882",
      |      "end_addr":"0x76aef000",
      |      "filename":"oleaut32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76b80000",
      |      "code_id":"4A5BD9DD4e000",
      |      "debug_file":"gdi32.pdb",
      |      "debug_id":"AF16A95F2D39406DA1912EE039CBF0652",
      |      "end_addr":"0x76bce000",
      |      "filename":"gdi32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76bd0000",
      |      "code_id":"4A5BDA19a000",
      |      "debug_file":"lpk.pdb",
      |      "debug_id":"B99319FE4427418F9EB5432B9F6A13412",
      |      "end_addr":"0x76bda000",
      |      "filename":"lpk.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76be0000",
      |      "code_id":"4A5BDAD96000",
      |      "debug_file":"nsi.pdb",
      |      "debug_id":"D15A81679FAE4A7392344B6FD26867942",
      |      "end_addr":"0x76be6000",
      |      "filename":"nsi.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76c80000",
      |      "code_id":"4A5BDAC715c000",
      |      "debug_file":"ole32.pdb",
      |      "debug_id":"21425202547E4A5DA0F12B17C4131B272",
      |      "end_addr":"0x76ddc000",
      |      "filename":"ole32.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76e40000",
      |      "code_id":"4A5BDADB13c000",
      |      "debug_file":"ntdll.pdb",
      |      "debug_id":"F0164DA71FAF4765B8F3DB4F2D7650EA2",
      |      "end_addr":"0x76f7c000",
      |      "filename":"ntdll.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76f80000",
      |      "code_id":"4A5BDB0419000",
      |      "debug_file":"sechost.pdb",
      |      "debug_id":"7AF14D02D41E4CD6942745FE0E6372B11",
      |      "end_addr":"0x76f99000",
      |      "filename":"sechost.dll",
      |      "version":"6.1.7600.16385"
      |    },
      |    {
      |      "base_addr":"0x76fa0000",
      |      "code_id":"4A5BDA69cc000",
      |      "debug_file":"msctf.pdb",
      |      "debug_id":"173DAEF86B2548DBA6134EB74C4D2F232",
      |      "end_addr":"0x7706c000",
      |      "filename":"msctf.dll",
      |      "version":"6.1.7600.16385"
      |    }
      |  ],
      |  "status":"OK",
      |  "threads":[
      |    {
      |      "frames":[
      |        {
      |          "ip":"0x6b0d7be7",
      |          "module_index":2,
      |          "trust":"context"
      |        },
      |        {
      |          "ip":"0x6b0d78d1",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0d789e",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0da79b",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0e7234",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0ec78c",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0ebea4",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0ec037",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0e7076",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0c5c4f",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0c1370",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0c13e6",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0bdd09",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6b0bde9e",
      |          "module_index":2,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6789a638",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6789b109",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6789afdf",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67c53c91",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6869757b",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x683a1329",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67751830",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67750523",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6774ff38",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6774fe77",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6774b300",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6774c7ac",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6836ed3f",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bd5ba8",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bd5b68",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bacd6e",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bacafe",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6983403a",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x6836ecfd",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bd5ba8",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x67bd5b68",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x69833e8b",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x69836eb3",
      |          "module_index":1,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x1208530",
      |          "module_index":0,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x12066ff",
      |          "module_index":0,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x12050da",
      |          "module_index":0,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x75691173",
      |          "module_index":63,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x76e9b3f4",
      |          "module_index":75,
      |          "trust":"frame_pointer"
      |        },
      |        {
      |          "ip":"0x76e9b3c7",
      |          "module_index":75,
      |          "trust":"frame_pointer"
      |        }
      |      ]
      |    }
      |  ]
      |}
    """.stripMargin
}
