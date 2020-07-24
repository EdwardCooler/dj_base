package jzero.admin.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("as_backup_record", "id", AsBackupRecord.class);
		arp.addMapping("as_common_setting", "id", AsCommonSetting.class);
		arp.addMapping("as_db_backup", "id", AsDbBackup.class);
		arp.addMapping("as_friendship_link", "id", AsFriendshipLink.class);
		arp.addMapping("as_front_nav", "id", AsFrontNav.class);
		arp.addMapping("as_log_email", "id", AsLogEmail.class);
		arp.addMapping("as_log_phone", "id", AsLogPhone.class);
		arp.addMapping("as_news_cat", "id", AsNewsCat.class);
		arp.addMapping("as_news_contents", "id", AsNewsContents.class);
		arp.addMapping("as_notes_model", "id", AsNotesModel.class);
		arp.addMapping("as_notes_send_history", "id", AsNotesSendHistory.class);
		arp.addMapping("as_phone_code", "id", AsPhoneCode.class);
		arp.addMapping("as_shedule_log", "id", AsSheduleLog.class);
	}
}
