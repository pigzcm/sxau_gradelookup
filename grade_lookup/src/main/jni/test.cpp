#include <cstring>
#include <jni.h>
#include <android/log.h>
#include <cstdio>
#include <cstdlib>
#include "yfdc_Test.h"
#include "com_sxau_soft_andsix_thread_LogoutThread.h"

#define LOGI(tag, fmt, args...) __android_log_print(ANDROID_LOG_INFO, tag, fmt, ##args)
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, fmt, ##args)
#define LOGW(tag, fmt, args...) __android_log_print(ANDROID_LOG_WARN, tag, fmt, ##args)

static const int LINES = 9;
static const int BUFSIZE1 = 1024;
static const int BUFSIZE2 = 255;
static const char *const LOG_TAG = "jni_张乘铭";

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_yfdc_Test_sleep1
		(JNIEnv *env, jclass clazz) {
	int a = 0;
	int b = 0;
	LOGI("YFDC", "%s", "正在打印九九乘法表:");
	LOGW(LOG_TAG, "%s", "[乘法表打印开始]");
	for (a = 1; a <= LINES; a++) {
		char buff[BUFSIZE1] = {0};
		memset(buff, 0, BUFSIZE1 * sizeof(char));
		for (b = 1; b <= a; b++) {
			char buff2[BUFSIZE2] = {0};
			memset(buff2, 0, BUFSIZE2 * sizeof(char));
			sprintf(buff2, "%dx%d=%2d  ", a, b, a * b);
			strcat(buff, buff2);
		}
		LOGD("%s", buff);
	}
	LOGW(LOG_TAG, "%s", "[乘法表打印完毕]");
}
JNIEXPORT jstring JNICALL Java_yfdc_Test_native1
		(JNIEnv *env, jclass clazz) {
	LOGD("%s", "constructing string");
	return env->NewStringUTF("欲觅一知己共度余生");
}
JNIEXPORT void JNICALL Java_com_sxau_soft_andsix_thread_LogoutThread_nativerun0
		(JNIEnv *abc, jobject def) {
	int **a;
	a = (int **) malloc(LINES * sizeof(int *));
	memset(a, 0, LINES * sizeof(int *));
	for (int i = 0; i < LINES; i++) {
		a[i] = (int *) malloc((i + 1) * sizeof(int));
		memset(a[i], 0, (i + 1) * sizeof(int));
	}
	for (int i = 0; i < LINES; i++) {
		a[i][0] = 1;
		a[i][i] = 1;
	}
	for (int i = 0; i < LINES; i++) {
		for (int j = 1; j <= i; j++) {
			if (i >= 1 && j >= 1 && j != i) {
				a[i][j] = (a[i - 1][j] + a[i - 1][j - 1]);
			}
		}
	}
	LOGD("%s", "打印杨辉三角");
	for (int i = 0; i < LINES; i++) {
		char vara[BUFSIZE1] = {0};
		memset(vara, 0, BUFSIZE1 * sizeof(char));
		for (int j = 0; j <= i; j++) {
			char varb[BUFSIZE1] = {0};
			memset(varb, 0, BUFSIZE1 * sizeof(char));
			sprintf(varb, "%3d ", a[i][j]);
			strcat(vara, varb);
		}
		LOGD("%s", vara);
	}
	for (int i = 0; i < LINES; i++) {
		free(a[i]);
		a[i] = nullptr;
	}
	free(a);
	a = nullptr;
}
#ifdef __cplusplus
}
#endif
