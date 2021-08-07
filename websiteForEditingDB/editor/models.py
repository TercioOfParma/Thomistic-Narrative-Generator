# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Basicinfo(models.Model):
    index = models.IntegerField(blank=True, null=True)
    type = models.TextField(db_column='TYPE', blank=True, null=True)  # Field name made lowercase.
    id = models.TextField(blank=True, null=True)
    postconditions_accept_output = models.TextField(db_column='POSTCONDITIONS_ACCEPT_OUTPUT', blank=True, null=True)  # Field name made lowercase.
    postconditions_reject_output = models.TextField(db_column='POSTCONDITIONS_REJECT_OUTPUT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    quotes_scripture = models.BooleanField(db_column='QUOTES_SCRIPTURE', blank=True, null=True)  # Field name made lowercase.
    scripture_bank_verses = models.TextField(db_column='SCRIPTURE_BANK_VERSES', blank=True, null=True)  # Field name made lowercase.
    postconditions_accept_consequential_actions = models.TextField(db_column='POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS', blank=True, null=True)  # Field name made lowercase.
    postconditions_reject_consequential_actions = models.TextField(db_column='POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'BasicInfo'


class Passions(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    love = models.FloatField(db_column='LOVE', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    pleasure = models.FloatField(db_column='PLEASURE', blank=True, null=True)  # Field name made lowercase.
    daring = models.FloatField(db_column='DARING', blank=True, null=True)  # Field name made lowercase.
    anger = models.FloatField(db_column='ANGER', blank=True, null=True)  # Field name made lowercase.
    hope = models.FloatField(db_column='HOPE', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'PASSIONS'


class VirtueCharity(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvice_hatredofgod = models.FloatField(db_column='SUBVICE_HATREDOFGOD', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvice_sloth = models.FloatField(db_column='SUBVICE_SLOTH', blank=True, null=True)  # Field name made lowercase.
    subvice_envy = models.FloatField(db_column='SUBVICE_ENVY', blank=True, null=True)  # Field name made lowercase.
    subvice_discord = models.FloatField(db_column='SUBVICE_DISCORD', blank=True, null=True)  # Field name made lowercase.
    subvice_contention = models.FloatField(db_column='SUBVICE_CONTENTION', blank=True, null=True)  # Field name made lowercase.
    subvice_schism = models.FloatField(db_column='SUBVICE_SCHISM', blank=True, null=True)  # Field name made lowercase.
    subvice_unjustwar = models.FloatField(db_column='SUBVICE_UNJUSTWAR', blank=True, null=True)  # Field name made lowercase.
    subvice_quarrelling = models.FloatField(db_column='SUBVICE_QUARRELLING', blank=True, null=True)  # Field name made lowercase.
    subvice_scandal = models.FloatField(db_column='SUBVICE_SCANDAL', blank=True, null=True)  # Field name made lowercase.
    virtue_charity = models.FloatField(db_column='VIRTUE_CHARITY', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_CHARITY'


class VirtueFaith(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvice_infidelity = models.FloatField(db_column='SUBVICE_INFIDELITY', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvice_heresy = models.FloatField(db_column='SUBVICE_HERESY', blank=True, null=True)  # Field name made lowercase.
    subvice_apostasy = models.FloatField(db_column='SUBVICE_APOSTASY', blank=True, null=True)  # Field name made lowercase.
    subvice_blasphemy = models.FloatField(db_column='SUBVICE_BLASPHEMY', blank=True, null=True)  # Field name made lowercase.
    virtue_faith = models.FloatField(db_column='VIRTUE_FAITH', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_FAITH'


class VirtueFortitude(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvirtue_magnanimity = models.FloatField(db_column='SUBVIRTUE_MAGNANIMITY', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_magnificence = models.FloatField(db_column='SUBVIRTUE_MAGNIFICENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_patience = models.FloatField(db_column='SUBVIRTUE_PATIENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_perseverance = models.FloatField(db_column='SUBVIRTUE_PERSEVERANCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_longanimity = models.FloatField(db_column='SUBVIRTUE_LONGANIMITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_mortification = models.FloatField(db_column='SUBVIRTUE_MORTIFICATION', blank=True, null=True)  # Field name made lowercase.
    subvice_fear = models.FloatField(db_column='SUBVICE_FEAR', blank=True, null=True)  # Field name made lowercase.
    subvice_fearlessness = models.FloatField(db_column='SUBVICE_FEARLESSNESS', blank=True, null=True)  # Field name made lowercase.
    subvice_audacity = models.FloatField(db_column='SUBVICE_AUDACITY', blank=True, null=True)  # Field name made lowercase.
    subvice_presumption = models.FloatField(db_column='SUBVICE_PRESUMPTION', blank=True, null=True)  # Field name made lowercase.
    subvice_ambition = models.FloatField(db_column='SUBVICE_AMBITION', blank=True, null=True)  # Field name made lowercase.
    subvice_inaneglory = models.FloatField(db_column='SUBVICE_INANEGLORY', blank=True, null=True)  # Field name made lowercase.
    subvice_parvificence = models.FloatField(db_column='SUBVICE_PARVIFICENCE', blank=True, null=True)  # Field name made lowercase.
    subvice_effeminacy = models.FloatField(db_column='SUBVICE_EFFEMINACY', blank=True, null=True)  # Field name made lowercase.
    subvice_pertinacity = models.FloatField(db_column='SUBVICE_PERTINACITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_diligence = models.FloatField(db_column='SUBVIRTUE_DILIGENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_perserverence = models.FloatField(db_column='SUBVIRTUE_PERSERVERENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_magnaimity = models.FloatField(db_column='SUBVIRTUE_MAGNAIMITY', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_FORTITUDE'


class VirtueHope(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    virtue_hope_subvice = models.FloatField(db_column='VIRTUE_HOPE_SUBVICE', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    virtue_hope = models.FloatField(db_column='VIRTUE_HOPE', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_HOPE'


class VirtueJustice(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvirtue_commutative = models.FloatField(db_column='SUBVIRTUE_COMMUTATIVE', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_legal = models.FloatField(db_column='SUBVIRTUE_LEGAL', blank=True, null=True)  # Field name made lowercase.
    subvirtue_distributive = models.FloatField(db_column='SUBVIRTUE_DISTRIBUTIVE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_restitution = models.FloatField(db_column='SUBVIRTUE_RESTITUTION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_religion = models.FloatField(db_column='SUBVIRTUE_RELIGION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_devotion = models.FloatField(db_column='SUBVIRTUE_DEVOTION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_adjuration = models.FloatField(db_column='SUBVIRTUE_ADJURATION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_piety = models.FloatField(db_column='SUBVIRTUE_PIETY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_patriotism = models.FloatField(db_column='SUBVIRTUE_PATRIOTISM', blank=True, null=True)  # Field name made lowercase.
    subvirtue_observances = models.FloatField(db_column='SUBVIRTUE_OBSERVANCES', blank=True, null=True)  # Field name made lowercase.
    subvirtue_dulia = models.FloatField(db_column='SUBVIRTUE_DULIA', blank=True, null=True)  # Field name made lowercase.
    subvirtue_obedience = models.FloatField(db_column='SUBVIRTUE_OBEDIENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_diligence = models.FloatField(db_column='SUBVIRTUE_DILIGENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_gratitude = models.FloatField(db_column='SUBVIRTUE_GRATITUDE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_justvindication = models.FloatField(db_column='SUBVIRTUE_JUSTVINDICATION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_truthfulness = models.FloatField(db_column='SUBVIRTUE_TRUTHFULNESS', blank=True, null=True)  # Field name made lowercase.
    subvirtue_friendship = models.FloatField(db_column='SUBVIRTUE_FRIENDSHIP', blank=True, null=True)  # Field name made lowercase.
    subvirtue_liberality = models.FloatField(db_column='SUBVIRTUE_LIBERALITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_epiekeia = models.FloatField(db_column='SUBVIRTUE_EPIEKEIA', blank=True, null=True)  # Field name made lowercase.
    subvice_humanrespect = models.FloatField(db_column='SUBVICE_HUMANRESPECT', blank=True, null=True)  # Field name made lowercase.
    subvice_murder = models.FloatField(db_column='SUBVICE_MURDER', blank=True, null=True)  # Field name made lowercase.
    subvice_mutilation = models.FloatField(db_column='SUBVICE_MUTILATION', blank=True, null=True)  # Field name made lowercase.
    subvice_theft = models.FloatField(db_column='SUBVICE_THEFT', blank=True, null=True)  # Field name made lowercase.
    subvice_robbery = models.FloatField(db_column='SUBVICE_ROBBERY', blank=True, null=True)  # Field name made lowercase.
    subvice_judgment = models.FloatField(db_column='SUBVICE_JUDGMENT', blank=True, null=True)  # Field name made lowercase.
    subvice_falseaccusation = models.FloatField(db_column='SUBVICE_FALSEACCUSATION', blank=True, null=True)  # Field name made lowercase.
    subvice_perjury = models.FloatField(db_column='SUBVICE_PERJURY', blank=True, null=True)  # Field name made lowercase.
    subvice_contumley = models.FloatField(db_column='SUBVICE_CONTUMLEY', blank=True, null=True)  # Field name made lowercase.
    subvice_detraction = models.FloatField(db_column='SUBVICE_DETRACTION', blank=True, null=True)  # Field name made lowercase.
    subvice_murmuring = models.FloatField(db_column='SUBVICE_MURMURING', blank=True, null=True)  # Field name made lowercase.
    subvice_derision = models.FloatField(db_column='SUBVICE_DERISION', blank=True, null=True)  # Field name made lowercase.
    subvice_malediction = models.FloatField(db_column='SUBVICE_MALEDICTION', blank=True, null=True)  # Field name made lowercase.
    subvice_usury = models.FloatField(db_column='SUBVICE_USURY', blank=True, null=True)  # Field name made lowercase.
    subvice_illicitadjuration = models.FloatField(db_column='SUBVICE_ILLICITADJURATION', blank=True, null=True)  # Field name made lowercase.
    subvice_idolatry = models.FloatField(db_column='SUBVICE_IDOLATRY', blank=True, null=True)  # Field name made lowercase.
    subvice_divination = models.FloatField(db_column='SUBVICE_DIVINATION', blank=True, null=True)  # Field name made lowercase.
    subvice_temptinggod = models.FloatField(db_column='SUBVICE_TEMPTINGGOD', blank=True, null=True)  # Field name made lowercase.
    subvice_sacrilege = models.FloatField(db_column='SUBVICE_SACRILEGE', blank=True, null=True)  # Field name made lowercase.
    subvice_simony = models.FloatField(db_column='SUBVICE_SIMONY', blank=True, null=True)  # Field name made lowercase.
    subvice_simulation = models.FloatField(db_column='SUBVICE_SIMULATION', blank=True, null=True)  # Field name made lowercase.
    subvice_boasting = models.FloatField(db_column='SUBVICE_BOASTING', blank=True, null=True)  # Field name made lowercase.
    subvice_irony = models.FloatField(db_column='SUBVICE_IRONY', blank=True, null=True)  # Field name made lowercase.
    subvice_adulation = models.FloatField(db_column='SUBVICE_ADULATION', blank=True, null=True)  # Field name made lowercase.
    subvice_litigious = models.FloatField(db_column='SUBVICE_LITIGIOUS', blank=True, null=True)  # Field name made lowercase.
    subvice_avarice = models.FloatField(db_column='SUBVICE_AVARICE', blank=True, null=True)  # Field name made lowercase.
    subvice_prodigality = models.FloatField(db_column='SUBVICE_PRODIGALITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_epieikeia = models.FloatField(db_column='SUBVIRTUE_EPIEIKEIA', blank=True, null=True)  # Field name made lowercase.
    subvirtue_judgement = models.FloatField(db_column='SUBVIRTUE_JUDGEMENT', blank=True, null=True)  # Field name made lowercase.
    subvice_stinginess = models.FloatField(db_column='SUBVICE_STINGINESS', blank=True, null=True)  # Field name made lowercase.
    subvice_judgement = models.FloatField(db_column='SUBVICE_JUDGEMENT', blank=True, null=True)  # Field name made lowercase.
    subvirtue_temptinggod = models.FloatField(db_column='SUBVIRTUE_TEMPTINGGOD', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_JUSTICE'


class VirtuePrudence(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvirtue_memory = models.FloatField(db_column='SUBVIRTUE_MEMORY', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_understanding = models.FloatField(db_column='SUBVIRTUE_UNDERSTANDING', blank=True, null=True)  # Field name made lowercase.
    subvirtue_docility = models.FloatField(db_column='SUBVIRTUE_DOCILITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_shrewdness = models.FloatField(db_column='SUBVIRTUE_SHREWDNESS', blank=True, null=True)  # Field name made lowercase.
    subvirtue_reason = models.FloatField(db_column='SUBVIRTUE_REASON', blank=True, null=True)  # Field name made lowercase.
    subvirtue_foresight = models.FloatField(db_column='SUBVIRTUE_FORESIGHT', blank=True, null=True)  # Field name made lowercase.
    subvirtue_circumspection = models.FloatField(db_column='SUBVIRTUE_CIRCUMSPECTION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_caution = models.FloatField(db_column='SUBVIRTUE_CAUTION', blank=True, null=True)  # Field name made lowercase.
    subvirtue_eubulia = models.FloatField(db_column='SUBVIRTUE_EUBULIA', blank=True, null=True)  # Field name made lowercase.
    subvirtue_synesis = models.FloatField(db_column='SUBVIRTUE_SYNESIS', blank=True, null=True)  # Field name made lowercase.
    subvirtue_gnome = models.FloatField(db_column='SUBVIRTUE_GNOME', blank=True, null=True)  # Field name made lowercase.
    subvice_precipitation = models.FloatField(db_column='SUBVICE_PRECIPITATION', blank=True, null=True)  # Field name made lowercase.
    subvice_inconsideration = models.FloatField(db_column='SUBVICE_INCONSIDERATION', blank=True, null=True)  # Field name made lowercase.
    subvice_inconstancy = models.FloatField(db_column='SUBVICE_INCONSTANCY', blank=True, null=True)  # Field name made lowercase.
    subvice_negligence = models.FloatField(db_column='SUBVICE_NEGLIGENCE', blank=True, null=True)  # Field name made lowercase.
    subvice_carnalprudence = models.FloatField(db_column='SUBVICE_CARNALPRUDENCE', blank=True, null=True)  # Field name made lowercase.
    subvice_craftiness = models.FloatField(db_column='SUBVICE_CRAFTINESS', blank=True, null=True)  # Field name made lowercase.
    subvice_guile = models.FloatField(db_column='SUBVICE_GUILE', blank=True, null=True)  # Field name made lowercase.
    subvice_fraud = models.FloatField(db_column='SUBVICE_FRAUD', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_PRUDENCE'


class VirtueTemperance(models.Model):
    index = models.IntegerField(blank=True, null=True)
    story_action_id = models.TextField(db_column='STORY_ACTION_ID', blank=True, null=True)  # Field name made lowercase.
    subvirtue_shame = models.FloatField(db_column='SUBVIRTUE_SHAME', blank=True, null=True)  # Field name made lowercase.
    is_precondition = models.BooleanField(db_column='IS_PRECONDITION', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_accept = models.BooleanField(db_column='IS_POSTCONDITION_ACCEPT', blank=True, null=True)  # Field name made lowercase.
    is_postcondition_reject = models.BooleanField(db_column='IS_POSTCONDITION_REJECT', blank=True, null=True)  # Field name made lowercase.
    second_person = models.BooleanField(db_column='SECOND_PERSON', blank=True, null=True)  # Field name made lowercase.
    third_person = models.BooleanField(db_column='THIRD_PERSON', blank=True, null=True)  # Field name made lowercase.
    is_above = models.BooleanField(db_column='IS_ABOVE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_honestia = models.FloatField(db_column='SUBVIRTUE_HONESTIA', blank=True, null=True)  # Field name made lowercase.
    subvirtue_abstinence = models.FloatField(db_column='SUBVIRTUE_ABSTINENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_fasting = models.FloatField(db_column='SUBVIRTUE_FASTING', blank=True, null=True)  # Field name made lowercase.
    subvirtue_sobriety = models.FloatField(db_column='SUBVIRTUE_SOBRIETY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_continence = models.FloatField(db_column='SUBVIRTUE_CONTINENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_chastity = models.FloatField(db_column='SUBVIRTUE_CHASTITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_virginity = models.FloatField(db_column='SUBVIRTUE_VIRGINITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_meekness = models.FloatField(db_column='SUBVIRTUE_MEEKNESS', blank=True, null=True)  # Field name made lowercase.
    subvirtue_modesty = models.FloatField(db_column='SUBVIRTUE_MODESTY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_humility = models.FloatField(db_column='SUBVIRTUE_HUMILITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_eutrapelia = models.FloatField(db_column='SUBVIRTUE_EUTRAPELIA', blank=True, null=True)  # Field name made lowercase.
    subvirtue_sportsmanship = models.FloatField(db_column='SUBVIRTUE_SPORTSMANSHIP', blank=True, null=True)  # Field name made lowercase.
    subvirtue_decorum = models.FloatField(db_column='SUBVIRTUE_DECORUM', blank=True, null=True)  # Field name made lowercase.
    subvirtue_silence = models.FloatField(db_column='SUBVIRTUE_SILENCE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_studiousity = models.FloatField(db_column='SUBVIRTUE_STUDIOUSITY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_simplicty = models.FloatField(db_column='SUBVIRTUE_SIMPLICTY', blank=True, null=True)  # Field name made lowercase.
    subvice_gluttony = models.FloatField(db_column='SUBVICE_GLUTTONY', blank=True, null=True)  # Field name made lowercase.
    subvice_lust = models.FloatField(db_column='SUBVICE_LUST', blank=True, null=True)  # Field name made lowercase.
    subvice_fornication = models.FloatField(db_column='SUBVICE_FORNICATION', blank=True, null=True)  # Field name made lowercase.
    subvice_cheating = models.FloatField(db_column='SUBVICE_CHEATING', blank=True, null=True)  # Field name made lowercase.
    subvice_rape = models.FloatField(db_column='SUBVICE_RAPE', blank=True, null=True)  # Field name made lowercase.
    subvice_adultery = models.FloatField(db_column='SUBVICE_ADULTERY', blank=True, null=True)  # Field name made lowercase.
    subvice_incest = models.FloatField(db_column='SUBVICE_INCEST', blank=True, null=True)  # Field name made lowercase.
    subvice_cruelty = models.FloatField(db_column='SUBVICE_CRUELTY', blank=True, null=True)  # Field name made lowercase.
    subvice_crudity = models.FloatField(db_column='SUBVICE_CRUDITY', blank=True, null=True)  # Field name made lowercase.
    subvice_immodesty = models.FloatField(db_column='SUBVICE_IMMODESTY', blank=True, null=True)  # Field name made lowercase.
    subvirtue_pride = models.FloatField(db_column='SUBVIRTUE_PRIDE', blank=True, null=True)  # Field name made lowercase.
    subvirtue_humiliy = models.FloatField(db_column='SUBVIRTUE_HUMILIY', blank=True, null=True)  # Field name made lowercase.
    subvice_masturbation = models.FloatField(db_column='SUBVICE_MASTURBATION', blank=True, null=True)  # Field name made lowercase.
    subvice_effeminacy = models.FloatField(db_column='SUBVICE_EFFEMINACY', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'VIRTUE_TEMPERANCE'
