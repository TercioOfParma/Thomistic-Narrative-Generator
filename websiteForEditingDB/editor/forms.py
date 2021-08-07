import models as md
from django.db import models
from django.forms import ModelForm


class BasicinfoForm(ModelForm):
    class Meta:
        model = md.Basicinfo
        fields = '__all__'


class PassionsForm(ModelForm):
    class Meta:
        model = md.Passions
        fields = '__all__'


class VirtueCharityForm(ModelForm):
    class Meta:
        model = md.VirtueCharity
        fields = '__all__'


class VirtueFaithForm(ModelForm):
    class Meta:
        model = md.VirtueFaith
        fields = '__all__'


class VirtueHopeForm(ModelForm):
    class Meta:
        model = md.VirtueHope
        fields = '__all__'


class VirtueFortitudeForm(ModelForm):
    class Meta:
        model = md.VirtueFortitude
        fields = '__all__'


class VirtueJusticeForm(ModelForm):
    class Meta:
        model = md.VirtueJustice
        fields = '__all__'


class VirtuePrudenceForm(ModelForm):
    class Meta:
        model = md.VirtuePrudence
        fields = '__all__'


class VirtueTemperanceForm(ModelForm):
    class Meta:
        model = md.VirtueTemperance
        fields = '__all__'

